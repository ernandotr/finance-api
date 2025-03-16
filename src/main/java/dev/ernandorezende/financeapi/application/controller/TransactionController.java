package dev.ernandorezende.financeapi.application.controller;

import dev.ernandorezende.financeapi.application.requests.TransactionResquest;
import dev.ernandorezende.financeapi.application.responses.TransactionResponse;
import dev.ernandorezende.financeapi.domain.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll() {
        return  ResponseEntity.ok(this.transactionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.transactionService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionResquest transactionResquest) {
        return ResponseEntity.ok(this.transactionService.create(transactionResquest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTransaction(@RequestBody TransactionResquest transactionResquest, @PathVariable Long id) {
        this.transactionService.update(transactionResquest, id);
        return ResponseEntity.noContent().build();
    }

}
