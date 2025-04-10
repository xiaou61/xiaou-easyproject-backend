package org.dromara.weixinqrcode.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Jwt工具类，生成JWT和认证
 */
@Slf4j
@Component("jwtutils2")
public class JwtUtil {

    @Resource
    private AesUtils aesUtils;

    public String secret="";

    private JWTVerifier verifier;

    @PostConstruct
    public synchronized void init() {
        log.info("init jwt verifier");
        verifier = JWT.require(Algorithm.HMAC256(secret)).build();
    }

    /**
     * 过期时间，单位为秒,3天
     **/
    private static final long EXPIRATION = 3 * 24 * 3600L;
    /**
     * open id
     */
    public static final String OPEN_ID = "oid";

    /**
     * 生成用户token,设置token超时时间
     */
    public String createToken(String openId) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
            // 添加头部
            .withHeader(map)
            // 可以将基本信息放到claims中
            .withClaim(OPEN_ID, openId)
            //超时设置,设置过期的日期
            .withExpiresAt(expireDate)
            //签发时间
            .withIssuedAt(new Date())
            //SECRET加密
            .sign(Algorithm.HMAC256(secret));
        return aesUtils.aesEncrypt(token);
    }

    /**
     * 校验token并解析token
     */
    public Map<String, Claim> verifyToken(String token) {
        try {
            token = aesUtils.aesDecrypt(token);
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            log.error(String.format("verifyToken error,token:%s,msg:%s", token, e.getMessage()), e);
            if (e instanceof TokenExpiredException) {
                throw new RuntimeException("jwt verify token error");
            }
        }
        return null;
    }

}
