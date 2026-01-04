package dev.ernandorezende.financeapi.domain.services;

import dev.ernandorezende.financeapi.application.requests.NewUserRequest;
import dev.ernandorezende.financeapi.domain.models.User;
import dev.ernandorezende.financeapi.infra.reposirories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(NewUserRequest request) {
        User user = mapToUser(request);
        userRepository.save(user);
    }

    private User mapToUser(NewUserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        return user;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}
