package com.xiaou.xiaoueasyprojectbackend.module.support.log.controller;


import com.xiaou.xiaoueasyprojectbackend.module.support.log.data.ReturnPageData;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.dto.LogDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.entity.Log;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.query.LogQuery;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.resp.Response;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.service.LogService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * 系统日志Controller
 *
 * @author xiaohai
 * @since 2023-03-30
 */
@Tag(name = "日志接口", description = "系统日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/log")
public class LogController {

    private final LogService logService;


    @DeleteMapping("{ids}")
    public Response<Integer> delete(@PathVariable Long[] ids) {
        return Response.success("删除系统日志成功！", logService.delete(ids));
    }


    @DeleteMapping("/all")
    public Response<Integer> deleteAll() {
        return Response.success("清空系统日志成功！", logService.deleteAll());
    }

    @GetMapping("{id}")
    public Response<Log> findById(@PathVariable Long id) {
        return Response.success("id查询系统日志成功！", logService.findById(id));
    }

    @Parameter(name = "pageNum", description = "页码", required = true)
    @Parameter(name = "pageSize", description = "每页数量", required = true)
    @GetMapping()
    public Response<ReturnPageData<LogDto>> findListByPage(@ParameterObject LogQuery query) {
        return Response.success("查询系统日志列表成功！", logService.findListByPage(query));
    }


    @GetMapping("/test")
    @com.xiaou.xiaoueasyprojectbackend.module.support.log.annotation.Log(title = "测试日志")
    public void test() {
        System.out.println("测试");
    }


}
