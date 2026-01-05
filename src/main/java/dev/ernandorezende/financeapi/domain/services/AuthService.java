package dev.ernandorezende.financeapi.domain.services;

import dev.ernandorezende.financeapi.domain.models.AuthRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    String getJwtToken(AuthRequest authRequest);

    String validateJwtToken(String authToken);
}
