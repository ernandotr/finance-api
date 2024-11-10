package dev.ernandorezende.financeapi.application.controller;

import dev.ernandorezende.financeapi.application.requests.CategoryRequest;
import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import dev.ernandorezende.financeapi.domain.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {

        return ResponseEntity.ok(this.categoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }
}
