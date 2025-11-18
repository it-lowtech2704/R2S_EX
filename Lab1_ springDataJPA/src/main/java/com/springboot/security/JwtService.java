package com.springboot.security;


import com.springboot.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final Long expMinutes;

    public JwtService(
            @Value("${jwt.secret}") String base64Secret,
            @Value("${jwt.exp-minutes:60}") long expMinutes
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));
        this.expMinutes = expMinutes;
    }
    public String generateToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder().subject(user.getUsername())
                .claim("role",user.getRole().name())
                .issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(expMinutes*60)))
                .signWith(secretKey).compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }
}
