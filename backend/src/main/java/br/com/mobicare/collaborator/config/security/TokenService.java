package br.com.mobicare.collaborator.config.security;

import br.com.mobicare.collaborator.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${playlist.jwt.expiration}")
    private String expirationTime;

    @Value("${playlist.jwt.secret}")
    private String secret;

    public String getToken(Authentication authenticate) {
        User logged = (User) authenticate.getPrincipal();
        Date current = new Date();
        Date expirationDate = new Date(current.getTime() + Long.parseLong(this.expirationTime));

        return Jwts.builder()
                .setIssuer("Collaborator API")
                .setSubject(logged.getId().toString())
                .setIssuedAt(current)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getUser(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(body.getSubject());
    }
}