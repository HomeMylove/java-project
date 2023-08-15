package com.homemylove.utils;

import com.homemylove.auth.AuthInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // 生成Token
    public static String generateToken(AuthInfo authInfo, long expirationTime, String secret) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authInfo.getId());
        claims.put("username", authInfo.getUsername());
        claims.put("name", authInfo.getName());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    // 验证Token并解析为对象
    public static AuthInfo verifyToken(String token, String secret) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("过期了");
        }
        AuthInfo authInfo = new AuthInfo();
        authInfo.setId(claims.get("id", Long.class));
        authInfo.setUsername(claims.get("username", String.class));
        authInfo.setName(claims.get("name", String.class));
        return authInfo;
    }
}

