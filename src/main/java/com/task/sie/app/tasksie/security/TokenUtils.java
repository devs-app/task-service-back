package com.task.sie.app.tasksie.security;

import com.task.sie.app.tasksie.model.Rol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class TokenUtils {

    private final static String SECRET_APP = "PrQ@LKZ%z$b@deuHMkmEU";

    private static final long EXPIRATION_APP =  600000000L;


    public static String generateToken(String username, String email){
        long expirationTime = EXPIRATION_APP;
        Date expire = new Date(new Date().getTime() + EXPIRATION_APP);

        Map<String, Object> claims = new HashMap<>();

        claims.put("username", username);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expire)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_APP.getBytes())
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_APP.getBytes()).parseClaimsJws(token).getBody();
            return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Collections.emptyList());

        }catch (Exception e){
            return null;
        }
    }
}
