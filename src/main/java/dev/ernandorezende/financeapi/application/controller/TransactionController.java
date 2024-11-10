package dev.ernandorezende.financeapi.application.controller;

import dev.ernandorezende.financeapi.application.requests.TransactionResquest;
import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import dev.ernandorezende.financeapi.application.responses.TransactionResponse;
import dev.ernandorezende.financeapi.domain.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll() {

        return  ResponseEntity.ok(transactionService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionResquest transactionResquest, HttpServletRequest request) {

        return ResponseEntity.ok(transactionService.create(transactionResquest));
    }

}
