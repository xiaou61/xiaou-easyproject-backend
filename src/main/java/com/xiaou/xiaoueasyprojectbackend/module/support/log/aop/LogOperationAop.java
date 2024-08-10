package com.xiaou.xiaoueasyprojectbackend.module.support.log.aop;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import com.aliyun.oss.ServiceException;
import com.xiaou.xiaoueasyprojectbackend.module.support.ip.utils.IpUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.annotation.Log;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.resp.Response;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.service.LogService;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.vo.LogVo;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Log注解日志操作
 * 例如：@Log(title = "用户模块")
 *
 * @author wangchenghai
 * @date 2022/3/20 9:36
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class LogOperationAop {

    private static final Object CONCEAL = "******";
    @Resource
    private LogService logService;

    /**
     * 定义了一个切入点
     * execution(* com.xiaohai.*.controller..*.*(..))
     *
     * @annotation(controllerLog)
     */
    @Pointcut(value = "@annotation(controllerLog)")
    public void methodAspect(Log controllerLog) {
    }

    @Before(value = "methodAspect(controllerLog)", argNames = "joinPoint,controllerLog")
    public void outInfo(JoinPoint joinPoint, Log controllerLog) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("【注解：Before】浏览器输入的网址=URL : " + request.getRequestURL().toString());
        log.info("【注解：Before】HTTP_METHOD : " + request.getMethod());
        log.info("【注解：Before】IP : " + request.getRemoteAddr());
        log.info("【注解：Before】执行的业务方法名=CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("【注解：Before】业务方法获得的参数=ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param
     * @author wangchenghai
     * @date 2022/3/20 10:26
     */

    @After(value = "methodAspect(controllerLog)", argNames = "controllerLog")
    public void after(Log controllerLog) {
        log.info("【注解：After】方法最后执行.....");
    }

    /**
     * 后置返回通知
     *
     * @param
     * @author wangchenghai
     * @date 2022/3/20 10:23
     */

    @AfterReturning(returning = "ret", value = "methodAspect(controllerLog)", argNames = "ret,controllerLog")
    public void afterReturning(Object ret, Log controllerLog) {
        // 处理完请求，返回内容
        log.info("【注解：AfterReturning】这个会在切面最后的最后打印，方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     *
     * @param
     * @author wangchenghai
     * @date 2022/3/20 10:25
     */

    @AfterThrowing(value = "methodAspect(controllerLog)", throwing = "e", argNames = "joinPoint,controllerLog,e")
    public void afterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        log.info("【注解：AfterThrowing】方法异常时执行....." + e.getMessage());
    }

    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     *
     * @param pjp
     * @return java.lang.Object
     * @author wangchenghai
     * @date 2022/3/20 10:26
     */

    @Around(value = "methodAspect(controllerLog)", argNames = "pjp,controllerLog")
    public Object around(ProceedingJoinPoint pjp, Log controllerLog) {
        log.info("【注解：Around . 环绕前】方法环绕start.....");
        Object o = null;
        Throwable ex = null;
        try {
            //如果不执行这句，会不执行切面的Before方法及controller的业务方法
            o = pjp.proceed();
            log.info("【注解：Around. 环绕后】方法环绕proceed，结果是 :" + o);
        } catch (IllegalArgumentException e) {
            //Assert业务异常
            log.error(e.getMessage());
            o = Response.failure(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (ServiceException e) {
            //业务异常
            log.error(e.getMessage());
            o = Response.failure(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Throwable e) {
            ex = e;
            throw new RuntimeException(e);
        } finally {
            handleLog(pjp, ex, o, controllerLog);
        }
        return o;
    }


    protected void handleLog(final JoinPoint joinPoint, final Throwable e, Object jsonResult, Log controllerLog) {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            LogVo log = new LogVo();
            log.setTitle(controllerLog.title());
            log.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.setRequestMethod(request.getMethod());
            log.setOperUrl(request.getRequestURL().toString());
            log.setOperIp(IpUtils.getIp(request));
            //获取UA信息
            UserAgent userAgent = IpUtils.getUserAgent(request);
            log.setOperOs(userAgent.getOperatingSystem().getName());
            log.setOperBrowser(userAgent.getBrowser().getName());
            if(log.getTitle().equals("登录")||log.getTitle().equals("用户信息注册")){
                //如果是登录将去除密码信息记录日志
                log.setOperParam(new JSONArray(joinPoint.getArgs()).getJSONObject(0).set("password",CONCEAL).toString());
            }else if(log.getTitle().equals("更改系统配置")){
                //如果是将去除邮箱密码信息记录日志
                log.setOperParam(new JSONArray(joinPoint.getArgs()).getJSONObject(0).set("emailPassword",CONCEAL).toString());
            }else if(log.getTitle().equals("新增草稿文章")||log.getTitle().equals("新增发布文章")||log.getTitle().equals("更新发布文章")){
                //如果是将去除文章内容信息记录日志
                log.setOperParam(new JSONArray(joinPoint.getArgs()).getJSONObject(0).set("text",CONCEAL).set("title",CONCEAL).set("summary",CONCEAL).toString());
            }else {
                log.setOperParam(new JSONArray(joinPoint.getArgs()).toString());
            }
            //异常
            if (e != null) {
                log.setStatus("1");
                log.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            } else {
                log.setStatus("0");
            }
            //返回参数
            if (jsonResult != null) {
                log.setJsonResult(new JSONObject(jsonResult).toString());
            }
            //todo 这里要自己修改
            log.setCreatedBy("系统");
            log.setCreatedTime(LocalDateTime.now());
            logService.add(log);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }
}
