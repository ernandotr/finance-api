package dev.ernandorezende.financeapi.domain.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.ernandorezende.financeapi.domain.models.AuthRequest;
import dev.ernandorezende.financeapi.domain.models.User;
import dev.ernandorezende.financeapi.infra.reposirories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public String getJwtToken(AuthRequest authRequest) {
        return generateJwtToken(userRepository.findByUsername(authRequest.username()));
    }

    public String generateJwtToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                            .withIssuer("auth-api")
                            .withSubject(user.getUsername())
                            .withExpiresAt(getExpiresAt())
                            .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while creating JWT token");
        }
    }

    public String validateJwtToken(String authToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
             return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build().verify(authToken)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid JWT token");
        }
    }

    private Instant getExpiresAt() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.UTC);
    }
}
