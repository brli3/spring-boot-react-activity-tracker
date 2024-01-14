package com.brli.myday.utils;

import com.brli.myday.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
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

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }
  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    return Jwts.builder()
            //.setSubject(jwtSubject)
            .setSubject(userPrincipal.getUsername())
            //.claim("id", userPrincipal.getId())
            //.claim("username", userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
            .signWith(key(), SignatureAlgorithm.HS256)
            //.signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }

  public boolean validateJwtToken(String authToken) {
    try {
    Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
    return true;
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
  private Claims getClaimsFromJwt(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
  }

  public String getUsernameFromJwt(String token) {
    return getClaimsFromJwt(token).getSubject();
  }

  public String getUserIdFromJwt(String token) {
    return getClaimsFromJwt(token).getId();
  }
}
