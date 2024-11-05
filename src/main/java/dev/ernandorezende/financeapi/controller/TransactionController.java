package dev.ernandorezende.financeapi.controller;

import dev.ernandorezende.financeapi.application.requests.TransactionResquest;
import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import dev.ernandorezende.financeapi.application.responses.TransactionResponse;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("api/v1/transactions")
public class TransactionController {

    Map<Long, TransactionResponse> transactions = new HashMap<>();


    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll() {
        var supermaket = new CategoryResponse(1, "Supermaket");
        var fuel = new CategoryResponse(2, "Fuels");
        var t1 =  new TransactionResponse(1L, "Pingo Doce", "Millennium", BigDecimal.valueOf(234.95), supermaket);
        var t2 =  new TransactionResponse(2L, "Galp", "Wallet", BigDecimal.valueOf(40.0), fuel);

        transactions.put(t1.id(), t1);
        transactions.put(t2.id(), t2);

        return  ResponseEntity.ok(transactions.values().stream().toList());
    }

    @PostMapping
    public ResponseEntity.BodyBuilder createTransaction(@RequestBody TransactionResquest transactionResquest, HttpServletRequest request) {
        String urlBase = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build().toString();
        Long id = transactions.keySet().stream().sorted().max(Comparator.comparingLong(Long::longValue)).get() + 1;

        var t  = new TransactionResponse(id, transactionResquest.target(), transactionResquest.source(),
                transactionResquest.value(), new CategoryResponse(transactionResquest.categoryId(), "Any"));
        transactions.put(id, t);
        return ResponseEntity.created(URI.create(urlBase));

    }

}
