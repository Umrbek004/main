package org.example.postproject.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTProvider {
    /**
     * Jwt ga qoshiladigan secret key
     * bu keysiz uni decode qilib bo'lamydi
     */
    @Value("${post.security.jwt.secret-key}")
    private String SECRET_KEY;
    /**
     * Jwt tokenning tugash vaqti
     */
    @Value("${post.security.jwt.expiration}")
    private long TOKEN_EXPIRATION;

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, TOKEN_EXPIRATION);
    }

    /**
     * Jwt yaratish
     */
    private String generateToken(UserDetails userDetails, long expiration) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signKey())
                .compact();
    }

    /**
     * jwt dan username ni ajratib olish
     */
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Jwt eskirmaganmi yo'qmi va ajratib olingan username bizga kirib kelayotgan username birxilligini tekshirish
     */
    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpiration(token));
    }

    private boolean isTokenExpiration(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private Key signKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}