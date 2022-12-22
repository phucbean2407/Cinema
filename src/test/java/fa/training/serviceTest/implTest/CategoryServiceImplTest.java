package fa.training.serviceTest.implTest;

import fa.training.dto.CategoryDTO;
import fa.training.entity.Category;
import fa.training.repository.CategoryRepository;
import fa.training.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;


    private CategoryDTO categoryDTO,categoryDTO2;
    Category category, category2;
    CategoryDTO exceptionCategory;
    @BeforeEach
    void setUp() {
        categoryDTO = new CategoryDTO();
        categoryDTO.setName("Cartoon");

        categoryDTO2 = new CategoryDTO();
        categoryDTO2.setName("Action");

        category = new Category();
        category.setName("Cartoon");
        category.setId(1L);

        category2 = new Category();
        category2.setName("Action");
        category2.setId(2L);


        exceptionCategory = new CategoryDTO();
        exceptionCategory.setName("Cartoon");

    }

    @Test
    void findByNameTest(){
        //Given
        //Exists on setUp()

        //When
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.of(category));
        //Then
        CategoryDTO categoryDTO1 = categoryService.findByName(categoryDTO.getName()).getBody();
        assertEquals(categoryDTO,categoryDTO1);
    }
    @Test
    void findByNameThrowNoSuchElementExceptionTest(){
        //Given
        //Exists on setUp()
        when(categoryRepository.findByName("ActionAAAA")).thenReturn(Optional.empty());
        //Then
        Exception exception = assertThrows(
                NoSuchElementException.class,
                () -> {
                    categoryService.findByName("ActionAAAA");
                }
        );
        assertEquals("NOT FOUND",exception.getMessage());
        assertTrue(exception.getMessage().contains("FOUND"));
    }
    @Test
    void addCategoryTest() {
        //Given
        when(categoryRepository.save(any(Category.class))).then(new Answer<Category>() {
            int sequence = 2;
            @Override
            public Category answer(InvocationOnMock invocation) throws Throwable {
                Category category = invocation.getArgument(0);
                category.setName(categoryDTO2.getName());
                category.setId(sequence++);
                return category;
            }
        });
        //Then
        CategoryDTO insertedDTO = categoryService.addCategory(categoryDTO2).getBody();
        verify(categoryRepository).save(category2);
        assertNotNull(category2);
        assertEquals(categoryDTO2,insertedDTO);
    }

}
