package dev.ernandorezende.financeapi.application.controller;

import dev.ernandorezende.financeapi.domain.models.AuthRequest;
import dev.ernandorezende.financeapi.domain.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody  AuthRequest authRequest) {
        var userAuthenticationToken = new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());

        authenticationManager.authenticate(userAuthenticationToken);
        return authService.getJwtToken(authRequest);
    }

    @PostMapping("/refresh")
    public void refresh() {

    }

    @GetMapping("/me")
    public void me() {

    }

}
