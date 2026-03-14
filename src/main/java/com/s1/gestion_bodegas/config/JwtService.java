package com.s1.gestion_bodegas.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "clave_super_secreta_para_clase_2026";

    private final long EXPIRATION = 1000 * 60 * 30; // 30 minutos


    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    public String generateToken(String username) {

        return Jwts.builder()

                .setSubject(username)


                .setIssuedAt(new Date())


                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))


                .signWith(getKey(), SignatureAlgorithm.HS256)

                .compact();
    }


    public String validateToken(String token) {

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()


                    .parseClaimsJws(token)


                    .getBody()


                    .getSubject();
        } catch (Exception e) {



            return null;
        }
    }
}