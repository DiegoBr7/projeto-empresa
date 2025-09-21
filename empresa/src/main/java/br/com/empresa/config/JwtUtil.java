package br.com.empresa.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

import static java.util.logging.Level.parse;

@Component
public class JwtUtil {
    private final Key key;
    private final long expirationMs;

    public JwtUtil(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-ms:8640000}") long expirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    public String generateToken(String username , Collection<String> roles){
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles" , roles)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key , SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token){
      return parse(token).getBody().getSubject();
    }

    public boolean validate(String token){
        try {
            parse(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
private Jws<Claims> parse(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
}

}
