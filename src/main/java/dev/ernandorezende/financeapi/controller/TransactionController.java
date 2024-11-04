package dev.ernandorezende.financeapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @GetMapping
    public ResponseEntity<List<String>> getAll() {
        List<String> transactions = List.of("Compra no supermercado", "Transporte Escolar");
        return  ResponseEntity.ok(transactions);
    }

}
