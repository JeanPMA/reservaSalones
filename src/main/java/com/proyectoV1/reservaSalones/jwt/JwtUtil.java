package com.proyectoV1.reservaSalones.jwt;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtUtil {
    private static final String secret = "jeanpoolclavesecretaa";
    private static SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    public String extraerUsuario(String token){
        return extractClaims(token, Claims::getSubject);
    }
    public Date extraerExpiracion(String token){
        return extractClaims(token, Claims::getExpiration);
    }
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        return extraerExpiracion(token).before(new Date());
    }
    public String generateToken(String usuario, String rol){
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", rol);
        return createToken(claims, usuario);
    }
    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    public  Boolean validateToken(String token, Usuario usuario){
        final String usuarioNombre = extraerUsuario(token);
        return (usuarioNombre.equals(usuario.getUsuario()) && !isTokenExpired(token));
    }
}
