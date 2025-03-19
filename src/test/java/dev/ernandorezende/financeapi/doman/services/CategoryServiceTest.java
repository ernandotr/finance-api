package dev.ernandorezende.financeapi.doman.services;

import dev.ernandorezende.financeapi.application.requests.CategoryRequest;
import dev.ernandorezende.financeapi.domain.models.Category;
import dev.ernandorezende.financeapi.domain.services.CategoryService;
import dev.ernandorezende.financeapi.infra.reposirories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    void createCategorySuccessTest() {
        var expectedCategory = new Category(1,"Supermarket");
        var request =  new CategoryRequest("Supermarket");
        when(categoryRepository.save(any(Category.class))).thenReturn(expectedCategory);
        categoryService.createCategory(request);
        verify(categoryRepository).save(any());

        assertThat(expectedCategory.getId()).isNotNull();
        assertThat(expectedCategory.getId()).isEqualTo(1);
    }

    @Test
    void updateCategorySuccessTest() {
        var expectedCategory = new Category(1,"Supermarket");
        var request =  new CategoryRequest("Supermarket");
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(expectedCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(expectedCategory);
        categoryService.update(request, 1);
        verify(categoryRepository).findById(anyInt());
        verify(categoryRepository).save(any());

        assertThat(expectedCategory.getId()).isNotNull();
        assertThat(expectedCategory.getId()).isEqualTo(1);
    }

    @Test
    void getAllCategoriesSuccess() {
        var category = new Category(1,"Supermarket");
        var expectedCategories = List.of(category);
        when(categoryRepository.findAll()).thenReturn(expectedCategories);
        var categories = categoryService.getAll();

        assertThat(categories).isNotNull();
        assertThat(categories).isNotEmpty();
        assertThat(categories.getFirst().id()).isEqualTo(1);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void findByIdSuccessful(){
        var expectedCategory = new Category(1,"Supermarket");
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(expectedCategory));
        var categoria = categoryService.getById(1);
        assertThat(categoria.id()).isEqualTo(1);
        verify(categoryRepository, times(1)).findById(anyInt());
    }

    @Test
    void deleteSuccessful(){
        categoryService.delete(1);
        verify(categoryRepository).deleteById(anyInt());
    }
}
