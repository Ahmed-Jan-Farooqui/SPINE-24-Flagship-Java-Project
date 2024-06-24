package dev.SPINE.project.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private final MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
    final String SECRET_KEY = "5d4a2e547a78336e7e6e283553487152535d3d4a58563e71723a465c77376d20";

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Convenience function where no extra claims are provided
    public String generateToken(UserDetails userDetails) {
        // Create token with an expiration of 24 hours.
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .signWith(getSecretKey(), macAlgorithm)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        // Create token with an expiration of 24 hours.
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .signWith(getSecretKey(), macAlgorithm)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
    }

    // Checks validity of the token.
    public boolean isTokenValid(String token, UserDetails userDetails){
        String actual_email = userDetails.getUsername();
        String token_email = extractEmail(token);
        return token_email.equals(actual_email) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        // Extracts the expiration date and checks if it is BEFORE the current date. If the statement below returns True, then it means
        // the token is expired.
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    // Template function that extracts a specific claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //Generate my SecretKey using the one defined
    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
