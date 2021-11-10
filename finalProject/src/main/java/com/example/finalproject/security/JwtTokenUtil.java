package com.example.finalproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenUtil implements Serializable {
    private final long JWT_VAILD_DURATION=3600*24*7;
    @Value("${jwt.secret}")
    private String secret;

    public <T>T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        Claims claims=getAllClaimFromToken(token);
        return claimsResolver.apply(claims);
    }
    public Claims getAllClaimFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public Boolean isTokenExpired(String token){
        final Date expirationDate=getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object>claims= new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+JWT_VAILD_DURATION*1000)).
                signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    public Boolean validToken(String token,UserDetails userDetails){
        final String username=getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername())&&isTokenExpired(token));
    }


    public String getUserNameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }
}
