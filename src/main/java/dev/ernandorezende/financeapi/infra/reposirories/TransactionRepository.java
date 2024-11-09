package dev.ernandorezende.financeapi.infra.reposirories;

import dev.ernandorezende.financeapi.domain.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
