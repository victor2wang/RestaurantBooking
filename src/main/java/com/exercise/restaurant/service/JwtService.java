package com.exercise.restaurant.service;

import com.exercise.restaurant.vo.SignIn;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class JwtService {


    public static final String SECRET = "a936bb62210853d0b3fe883e9419119fd2dd1b70c21725eac004f5078a02d89e";

    public JwtService() {
    }

    public String generateToken(String userName){
    Map<String,Object> claims = new HashMap<String,Object>();
    return createYoken(claims,userName);

}

private String createYoken(Map<String, Object> claims, String userName) {
  return Jwts.builder()
          .setClaims(claims)
          .setSubject(userName)
          .setIssuedAt(new Date(System.currentTimeMillis()))
          .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
          .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
}

public boolean validateToken(String token, String userName){
    String userNameInToken = exactUserName(token);
    return userNameInToken.equals(userName) && !isTokenExpired(token);

}

private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
}

private String exactUserName(String token) {
    return extractClaim(token, Claims::getSubject);
}

private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
}

public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
}

public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
}

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}