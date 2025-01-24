package com.cursos.api.springsecuritycourse.service.auth;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {
        // claims: propiedades del payload
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_IN_MINUTES * 60000));

        String jwt = Jwts.builder()
            .setClaims(extraClaims) // declarar payload
            .setSubject(user.getUsername()) // declarar nombre de usuario en el subject del jwt
            .setIssuedAt(issuedAt) // fecha de emisión
            .setExpiration(expiration) // fecha de expiración
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // tipo de token declarado en el header
            .signWith(generateKey(), SignatureAlgorithm.HS256) // firma del token
            .compact(); // devuelve en String el JWT

        return jwt;
    }

    private Key generateKey() {
        byte[] key = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(key);
    }
}
