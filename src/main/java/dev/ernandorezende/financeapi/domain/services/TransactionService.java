package dev.ernandorezende.financeapi.domain.services;

import dev.ernandorezende.financeapi.application.requests.TransactionResquest;
import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import dev.ernandorezende.financeapi.application.responses.TransactionResponse;
import dev.ernandorezende.financeapi.domain.exceptions.CategoryNotFoundException;
import dev.ernandorezende.financeapi.domain.exceptions.TransactionNotFoundException;
import dev.ernandorezende.financeapi.domain.models.Category;
import dev.ernandorezende.financeapi.domain.models.Transaction;
import dev.ernandorezende.financeapi.infra.reposirories.CategoryRepository;
import dev.ernandorezende.financeapi.infra.reposirories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<TransactionResponse> getAll(){
        return transactionRepository.findAll().stream()
                .map(this::toTransactionResponse).toList();
    }

    public TransactionResponse getById(Long id) {
        var transaction = transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        return toTransactionResponse(transaction);
    }

    public void delete(Long id) {
        this.transactionRepository.deleteById(id);
    }

    public TransactionResponse create(TransactionResquest request) {
        Transaction transaction = transactionRepository.save(toEntity(request));
        return toTransactionResponse(transaction);
    }

    public void  update(TransactionResquest request, Long id) {
        Transaction transaction =  transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        transaction.setTransactionValue(request.value());
        transaction.setCategory(categoryRepository.findById(request.categoryId()).orElseThrow(CategoryNotFoundException::new));
        transaction.setTarget(request.target());
        transaction.setSource(request.source());
        transactionRepository.save(transaction);
    }

    private TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(transaction.getId(), transaction.getTarget(), transaction.getSource(), 
                transaction.getTransactionValue(), toCategoryResponse(transaction.getCategory()));
    }

    private static CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    private Transaction toEntity(TransactionResquest request) {
        var category = this.categoryRepository.findById(request.categoryId()).orElseThrow(CategoryNotFoundException::new);
        return new  Transaction(request.target(), request.value(),  request.source(), category);
    }
}
