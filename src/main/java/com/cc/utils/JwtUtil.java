package com.cc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
public class JwtUtil {
    // 过期时间 5min
    private static final Long EXPIRE_TIME = 60 * 60 * 1000L;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";

    // 生成token，5min后过期
    public static String createToken(String username) {
        String token = null;
        try {
            // 过期时间
            Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 加密算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("Failed to create token. {}", e.getMessage());
        }
        return token;
    }

    // 验证token
    public static boolean verify(String token, String username) {
        boolean isSuccess = false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            // 验证token
            verifier.verify(token);
            isSuccess = true;
        } catch (UnsupportedEncodingException e) {
            log.error("Token is invalid. {}", e.getMessage());
        }
        return isSuccess;
    }

    // 获取token中的信息，无需secret解密也能获得
    public static String getUsernameFromToken(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            String username = decode.getClaim("username").asString();
            return username;
        } catch (JWTDecodeException e) {
            log.error("Failed to Decode jwt. {}", e.getMessage());
            return null;
        }
    }
}
