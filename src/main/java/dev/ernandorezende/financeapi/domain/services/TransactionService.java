package dev.ernandorezende.financeapi.domain.services;

import dev.ernandorezende.financeapi.domain.models.Transaction;
import dev.ernandorezende.financeapi.infra.reposirories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }
}
