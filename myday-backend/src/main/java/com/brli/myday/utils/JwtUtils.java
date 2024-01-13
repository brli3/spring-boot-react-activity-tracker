package com.brli.myday.utils;

import com.brli.myday.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author brandon
 * 2022-09-16
 */
@Slf4j
@Component
public class JwtUtils {
  @Value("${jwt.subject}")
  private String jwtSubject;

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    return Jwts.builder()
            .setSubject(jwtSubject)
            .claim("id", userPrincipal.getId())
            .claim("username", userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }

  public Claims getClaimsFromJwt(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
  }

  public String getUsernameFromJwt(String token) {
    return (String) getClaimsFromJwt(token).get("username");
  }

  public String getUserIdFromJwt(String token) {
    return String.valueOf(getClaimsFromJwt(token).get("id"));
  }

  public boolean validateJwtToken(String authToken) {		try {
    Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    return true;
  } catch (SignatureException e) {
    log.error("Invalid JWT signature: {}", e.getMessage());
  } catch (MalformedJwtException e) {
    log.error("Invalid JWT token: {}", e.getMessage());
  } catch (ExpiredJwtException e) {
    log.error("JWT token is expired: {}", e.getMessage());
  } catch (UnsupportedJwtException e) {
    log.error("JWT token is unsupported: {}", e.getMessage());
  } catch (IllegalArgumentException e) {
    log.error("JWT claims string is empty: {}", e.getMessage());
  }
    return false;
  }
}
