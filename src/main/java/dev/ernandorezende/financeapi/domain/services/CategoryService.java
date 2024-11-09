package dev.ernandorezende.financeapi.domain.services;

import dev.ernandorezende.financeapi.application.requests.CategoryRequest;
import dev.ernandorezende.financeapi.application.responses.CategoryResponse;
import dev.ernandorezende.financeapi.domain.exceptions.CategoryNotFoundException;
import dev.ernandorezende.financeapi.domain.models.Category;
import dev.ernandorezende.financeapi.infra.reposirories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(CategoryRequest request){
        var category =  Category.builder()
                .name(request.name())
                .build();
        category = categoryRepository.save(category);
        return new CategoryResponse(category.getId(), category.getName());

    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream().map(category -> new CategoryResponse(category.getId(), category.getName())).toList();
    }

    public CategoryResponse getById(Integer id) {
        var category =  categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return new CategoryResponse(category.getId(), category.getName());
    }
}
