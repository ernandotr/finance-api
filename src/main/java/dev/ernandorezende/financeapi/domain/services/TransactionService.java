package dev.ernandorezende.financeapi.domain.services;

import dev.ernandorezende.financeapi.application.requests.TransactionResquest;
import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import dev.ernandorezende.financeapi.application.responses.TransactionResponse;
import dev.ernandorezende.financeapi.domain.exceptions.CategoryNotFoundException;
import dev.ernandorezende.financeapi.domain.models.Category;
import dev.ernandorezende.financeapi.domain.models.Transaction;
import dev.ernandorezende.financeapi.infra.reposirories.CategoryRepository;
import dev.ernandorezende.financeapi.infra.reposirories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public List<TransactionResponse> getAll(){
        return transactionRepository.findAll().stream()
                .map(this::toTransactionResponse).toList();
    }

    private TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(), transaction.getTarget(), transaction.getSource(), transaction.getTransactionValue(),
                toCategoryResponse(transaction.getCategory()));
    }

    private static CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    public TransactionResponse create(TransactionResquest resquest) {
        var category = categoryRepository.findById(resquest.categoryId()).orElseThrow(CategoryNotFoundException::new);
        var transaction = Transaction.builder().
                transactionValue(resquest.value())
                .category(category)
                .target(resquest.target())
                .source(resquest.source())
                .build();
        transaction = transactionRepository.save(transaction);
        return toTransactionResponse(transaction);
    }
}
