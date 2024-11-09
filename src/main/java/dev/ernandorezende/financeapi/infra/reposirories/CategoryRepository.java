package dev.ernandorezende.financeapi.infra.reposirories;

import dev.ernandorezende.financeapi.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
