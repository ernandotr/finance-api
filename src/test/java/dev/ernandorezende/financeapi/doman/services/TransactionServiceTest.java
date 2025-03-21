package dev.ernandorezende.financeapi.doman.services;

import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import dev.ernandorezende.financeapi.application.responses.TransactionResponse;
import dev.ernandorezende.financeapi.domain.models.Category;
import dev.ernandorezende.financeapi.domain.models.Transaction;
import dev.ernandorezende.financeapi.domain.services.TransactionService;
import dev.ernandorezende.financeapi.infra.reposirories.TransactionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    void getAllTransactionsSuccess() {
        transactionService.getAll();

        verify(transactionRepository).findAll();
    }

    @Test
    void getTransactiponByIdSuccess() {
        CategoryResponse categoryResponse = new CategoryResponse(1, "Market");
        var expectedTransaction = new TransactionResponse(1L, "Cobtinente", "Wallet", BigDecimal.TEN, categoryResponse);
        var category = new Category(1, "Market");
        var transaction =  new Transaction("Continente", BigDecimal.TEN, "Wallet", category);
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(transaction));
        transactionService.getById(1L);

        verify(transactionRepository, times(1)).findById(anyLong());

        Assertions.assertThat(expectedTransaction.id()).isEqualTo(1L);
    }
}
