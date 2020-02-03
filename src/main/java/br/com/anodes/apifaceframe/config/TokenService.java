package br.com.anodes.apifaceframe.config;

import br.com.anodes.apifaceframe.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;


    public String createToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Date hoje = new Date();
        Date dateExpiration = new Date(hoje.getTime() + new Long(expiration));

        return Jwts.builder()
                    .setIssuer("Teste Frame")
                    .setSubject(user.getId().toString())
                    .setIssuedAt(hoje)
                    .setExpiration(dateExpiration)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
