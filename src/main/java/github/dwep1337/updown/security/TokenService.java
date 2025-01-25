package github.dwep1337.updown.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import github.dwep1337.updown.config.DotEnvConfig;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String TOKEN_SECRET = DotEnvConfig.dotenv().get("JWT_SECRET");


    public String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            Instant oneDay = LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC);

            return JWT.create().withIssuer("updown-backend")
                    .withSubject(username)
                    .withExpiresAt(oneDay).sign(algorithm);
        } catch (JWTCreationException ex) {
            return null;
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            return JWT.require(algorithm)
                    .withIssuer("updown-backend")
                    .build().verify(token).getSubject();
        } catch (JWTVerificationException ex) {
            return null;
        }

    }
}
