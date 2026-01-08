package dev.ernandorezende.financeapi.application.controller;

import dev.ernandorezende.financeapi.application.requests.CategoryRequest;
import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import dev.ernandorezende.financeapi.domain.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(this.categoryService.createCategory(categoryRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.categoryService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody CategoryRequest categoryRequest,  @PathVariable Integer id) {
        this.categoryService.update(categoryRequest, id);
        return ResponseEntity.noContent().build();
    }
}
