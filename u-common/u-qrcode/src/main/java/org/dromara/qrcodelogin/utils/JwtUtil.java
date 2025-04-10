package org.dromara.qrcodelogin.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "9dad5e7e-bcb7-438f-b39e-ad8c67814915";

    public static  String generateAuthToken(String uuid) {
        // 生成JWT或其他形式令牌
        return Jwts.builder()
            .setSubject(uuid)
            .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1小时过期
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
            .compact();
    }
}
