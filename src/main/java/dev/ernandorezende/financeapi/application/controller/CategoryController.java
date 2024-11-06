package dev.ernandorezende.financeapi.application.controller;

import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    public ResponseEntity<List<CategoryResponse>> getAll() {
        CategoryResponse supermaket = new CategoryResponse(1, "Supermaket");
        return ResponseEntity.ok(List.of(supermaket));
    }
}
