package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * Generates and validates HS256-signed JWTs, per SRS: "stateless JWT-based
 * authentication secured using HS256 token signing mechanisms."
 *
 * Note on naming: the SRS's Project Structure file tree lists this class as
 * config/JwtService.java, while the Security & Filter Layer section
 * describes it as security/JwtUtil.java with generateToken(String username).
 * This implementation follows the section text (JwtUtil, security package)
 * since it defines the actual method contract; the file tree entry appears
 * to be a naming inconsistency in the source document.
 */
@Component
public class JwtUtil {

    // Injected from application.properties (e.g. jwt.secret / jwt.expiration).
    // A real secret must be a Base64-encoded string long enough for HS256
    // (256 bits minimum) -- never hardcode this in source in a real deploy.
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String expectedUsername) {
        String username = extractUsername(token);
        return username.equals(expectedUsername) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}