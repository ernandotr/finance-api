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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    void createCategorySuccessTest() {
        var expectedCategory = new Category("Supermarket");
        expectedCategory.setId(1);
        var request =  new CategoryRequest("Supermarket");
        when(categoryRepository.save(any(Category.class))).thenReturn(expectedCategory);
        categoryService.createCategory(request);
        verify(categoryRepository).save(any());

        assertThat(expectedCategory.getId()).isNotNull();
        assertThat(expectedCategory.getId()).isEqualTo(1);
    }
}
