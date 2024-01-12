package com.example.Authentication.CustomFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@AllArgsConstructor
@Service
public class JWTservice{
    private final String secrert_key="c9ea7d47e20a6c430e4986b24d74c2e7f8656e5fd8748abad14fff1b197c4579";



    public String GenerateToken(UserDetails userDetails){
        return CreateToken(new HashMap<>(),userDetails);
    }
    public String CreateToken(
            Map<String,Object> My_Claims,
            UserDetails userDetails
    ){
        return Jwts.
                builder()
                .signWith(getSignkey())
                .setClaims(My_Claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60* 24))
                .compact();
    }
    public String extractusername(String token){
        return Extractitem(token,Claims::getSubject) ;
    }

    public boolean isvalid(String token, UserDetails userDetails){
        String username=extractusername(token);
        return (username.equals(userDetails.getUsername())&&isnotexpired(token));
    }
    public boolean isnotexpired(String token){
        return Extractitem(token,Claims::getExpiration).before(new Date());
    }

    private Claims ExtractAllClaims(String token){
        return Jwts.
                parserBuilder()
                .setSigningKey(getSignkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    private <T> T Extractitem(String token, Function<Claims,T> claimsTFunction){
        Claims claims=ExtractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    private Key getSignkey() {
        byte[]key= Decoders.BASE64.decode(secrert_key);
        return Keys.hmacShaKeyFor(key);
    }

}
