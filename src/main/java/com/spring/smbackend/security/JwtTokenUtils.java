package com.spring.smbackend.security;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@Log4j2
@Component
public class JwtTokenUtils {

    /**
     * private final String CLAIMS_SUBJECT = "sub";
     * private final String CLAIMS_CREATED = "created";
     */
    private static String TOKEN_SECRET;
    private static Long ACCESS_TOKEN_VALIDITY;
    private static Long REFRESH_TOKEN_VALIDITY;

    public JwtTokenUtils(@Value("${auth.secret}") String secret,
                         @Value("${auth.access.expiration}") Long accessValidity,
                         @Value("${auth.refresh.expiration}") Long refreshValidity) {
        Assert.notNull(accessValidity, "Validity must not be null or empty");
        Assert.notNull(secret, "Validity must not be null or empty");
        TOKEN_SECRET = secret;
        ACCESS_TOKEN_VALIDITY = accessValidity;
        REFRESH_TOKEN_VALIDITY = refreshValidity;
    }

    /* *@Value("${expiration}")
    private Long TOKEN_VALIDITY = 604800L;

    @Value("${secret}")
    private String TOKEN_SECRET;*/

    /*
     ** public String generateToken(UserDetails userDetails) {
     * Map<String, Object> claims = new HashMap<>();
     * claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
     * claims.put(CLAIMS_CREATED, new Date());
     * <p>
     * return Jwts.builder()
     * .setClaims(claims)
     * .setExpiration(generateExpirationDate())
     * .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
     * .compact();
     * }
     */

    public static String generateToken(final String username, final String tokenId, boolean isRefresh) {
        return Jwts.builder()
                .setId(tokenId)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setIssuer("app-Service")
                .setExpiration(calcTokenExpirationDate(isRefresh))
                .claim("created", Calendar.getInstance().getTime())
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
    }

    private static Date calcTokenExpirationDate(boolean isRefresh) {
        return new Date(System.currentTimeMillis() + (isRefresh ? REFRESH_TOKEN_VALIDITY : ACCESS_TOKEN_VALIDITY) * 1000);
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getTokenIdFromToken(String token) {
        return getClaims(token).getId();
    }

    /* *private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY * 1000);
    }*/

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            claims = null;
        }
        return claims;
    }

    public boolean validateToken(String token, HttpServletRequest httpServletRequest) {
        try {
            Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.info("Invalid JWT Signature");
            //throw new SecurityException("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            log.info("Invalid JWT token");
            //throw new SecurityException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("Expired JWT token");
            httpServletRequest.setAttribute("expired", ex.getMessage());
            //throw new SecurityException("security.token_expired");
        } catch (UnsupportedJwtException ex) {
            log.info("Unsupported JWT exception");
            //throw new SecurityException("security.token_expired");
        } catch (IllegalArgumentException ex) {
            log.info("Jwt claims string is empty");
            //throw new SecurityException("Jwt claims string is empty");
        }
        return false;
    }
}
