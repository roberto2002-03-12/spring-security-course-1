package com.cursos.api.springsecuritycourse.service.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.MacAlgorithm;
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

        // String jwt = Jwts.builder()
        //     .setClaims(extraClaims) // declarar payload
        //     .setSubject(user.getUsername()) // declarar nombre de usuario en el subject del jwt
        //     .setIssuedAt(issuedAt) // fecha de emisión
        //     .setExpiration(expiration) // fecha de expiración
        //     .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // tipo de token declarado en el header
        //     .signWith(generateKey(), SignatureAlgorithm.HS256) // firma del token
        //     .compact(); // devuelve en String el JWT

        String jwt = Jwts.builder()
            .header()
              .type("JWT")
              .and()
            .subject(user.getUsername())
            .issuedAt(issuedAt)
            .expiration(expiration)
            .claims(extraClaims)
            .signWith(generateKey())
            .compact();

        return jwt;
    }

    private Key generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);

        // byte[] key = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        // return Jwts.parserBuilderr()// extraer el payload y pasarlo a un objeto de tipo claimsr
        //         .setSigningKey(generateKey()) // definir la clave que se utiliza para firmar
        //         .build() // Crear instancia JwtParser
        //         .parseClaimsJws(token) // extraer los claims del jwt con un token con firma
        //         // parseClaimsJwt: extraer sin firma
        //         .getBody(); // obtener objeto

        return Jwts.parser() // extraer el payload y pasarlo a un objeto de tipo claims
                .verifyWith((SecretKey) generateKey()) // definir la clave que se utiliza para firmar
                .build() // Crear instancia JwtParser
                .parseSignedClaims(token) // extraer los claims del jwt con un token con firma
                // parseClaimsJwt: extraer sin firma
                .getPayload(); // obtener objeto
    }
}
