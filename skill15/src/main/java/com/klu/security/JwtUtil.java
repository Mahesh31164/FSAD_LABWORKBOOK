package com.klu.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;
@Component
public class JwtUtil {

    // 🔐 Secret key (MUST be at least 32 characters)
    private final String SECRET = "mysecretkeymysecretkeymysecretkey";

    // 🔑 Generate Key

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    // 🔥 Generate JWT Token
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getKey())
                .compact();
    }

    // 🔍 Extract Claims (CORE METHOD)
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())   // ✅ Required in 0.13.0
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 👤 Extract Username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 🧾 Extract Role
    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    // ⏳ Check Token Expiry
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // ✅ Validate Token
    public boolean validateToken(String token, String username) {
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }
}