package com.ahmed.leavemanagement.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JwtService {


    private final String secret =
            "mysupersecretkeymysupersecretkey123456";


    public String generateToken(String email){

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                + 86400000
                        )
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                secret.getBytes()
                        )
                )
                .compact();
    }



    public String extractUsername(String token){

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                secret.getBytes()
                        )
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }



    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ){

        String username = extractUsername(token);

        return username.equals(
                userDetails.getUsername()
        );
    }

}