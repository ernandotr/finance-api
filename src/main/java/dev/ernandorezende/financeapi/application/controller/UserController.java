package dev.ernandorezende.financeapi.application.controller;

import dev.ernandorezende.financeapi.application.requests.NewUserRequest;
import dev.ernandorezende.financeapi.domain.models.User;
import dev.ernandorezende.financeapi.domain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody NewUserRequest request) {
        userService.save(request);

    }

    @GetMapping
    @PreAuthorize( "hasRole('ADMIN')")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }
}
