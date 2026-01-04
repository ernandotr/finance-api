package dev.ernandorezende.financeapi.infra.reposirories;

import dev.ernandorezende.financeapi.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
