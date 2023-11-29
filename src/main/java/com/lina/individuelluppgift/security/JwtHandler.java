package com.lina.individuelluppgift.security;

import com.lina.individuelluppgift.user.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.UUID;

@Component
public class JwtHandler {
    private String secretKey = "jajskfllakdmmfkdkirnfn123";
    private Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public String generateToken(User user){
        String userId = String.valueOf(user.getId());
        String username = user.getUsername();

        return Jwts.builder()
                .setSubject(userId)
                .claim("id", userId)
                .claim("username", username)
                .signWith(key)
                .compact();
    }

}
