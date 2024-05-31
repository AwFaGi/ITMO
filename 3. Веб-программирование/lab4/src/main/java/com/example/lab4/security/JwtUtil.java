package com.example.lab4.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

    @Value("${jwt_secret}")
    private String jwtSecret;

    public String generateToken(String username){
        return Jwts.builder().
                setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 1800000)).
                setSubject(username).signWith(SignatureAlgorithm.HS512, jwtSecret).
                compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().
                setSigningKey(jwtSecret).
                parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
