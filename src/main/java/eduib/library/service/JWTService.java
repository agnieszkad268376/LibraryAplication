package eduib.library.service;

import eduib.library.commonTypes.UserRole;
import eduib.library.entity.AuthEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JWT related methods.
 */
@Service
public class JWTService {

    private long tokenLifetime = 1000 * 60 *20;

    @Value("${token.signing.key}")
    private String jwtSignigKey;

    /**
     * Generates a JWT token
     * @param userDetail User details (AuthEntity)
     * @return Generated JWT token. (String)
     */
    public String generateToken(AuthEntity userDetail){
        return generateToken(new HashMap<>(), userDetail);
    }

    /**
     * Extracts the role from JWT token.
     * @param token JWT token (String)
     * @return Role extracted from the token. (UserRole)
     */
    public UserRole extractRole(String token){
        String roleString =  extractClaim(token, (claims) -> claims.get("role", String.class));
        return UserRole.valueOf(roleString);
    }

    /**
     * Validates the token
     * @param token JWT token (String)
     * @return True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token){
        try {
            return !isTokenExpired(token);
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Extracts the username from  JWT token.
     * @param token JWT token (String)
     * @return Username extracted from the token.
     */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Check expiration of the JWT token.
     * @param token JWT token (String)
     * @return true if the token is not expired, false otherwise
     */
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the Expiration of JWT token.
     * @param token JWT token (String)
     * @return Expiration of the token (Date)
     */
    private Date extractExpiration(String token ) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts claims of the token
     * @param token JWT token (String)
     * @return generic object
     */
    private <T> T  extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /**
     * Extracts the username from  JWT token.
     * @param token JWT token (String)
     * @return Claims
     */
    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     *  Generate  JWT token
     * @param extraClaims
     * @param userDetails
     * @return token (String)
     */
    private String generateToken(Map<String, Object> extraClaims, AuthEntity userDetails){
        extraClaims.put("role", userDetails.getRole());
        return Jwts.builder().claims(extraClaims)
                .subject(userDetails.getUserName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLifetime))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSignigKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
