package dev.ernandorezende.financeapi.doman.services;

import dev.ernandorezende.financeapi.domain.services.TransactionService;
import dev.ernandorezende.financeapi.infra.reposirories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    void getAllTransactionsSuccess() {

    }
}
