package fa.training.serviceTest.implTest;

import fa.training.dto.CategoryDTO;
import fa.training.entity.Category;
import fa.training.mapper.CategoryMapper;
import fa.training.repository.CategoryRepository;
import fa.training.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;


    private CategoryDTO categoryDTO1,categoryDTO2;
    Category category1, category2;
    CategoryDTO exceptionCategory;
    @BeforeEach
    void setUp() {
        categoryDTO1 = CategoryDTO.builder().name("Cartoon").build();

        categoryDTO2 = CategoryDTO.builder().name("Action").build();

        category1 = new Category();
        category1.setName("Cartoon");
        category1.setId(1L);

        category2 = new Category();
        category2.setName("Action");
        category2.setId(2L);


       exceptionCategory = CategoryDTO.builder()
               .name("Cartoon").build();

    }

    @Test
    void findByNameTest(){
        //Given
        Category categoryExpected = new Category();
        categoryExpected.setName("Cartoon");
        CategoryDTO categoryDTOExpected = CategoryDTO.builder()
                .name("Cartoon").build();
        //When
        when(categoryRepository.findByName("Cartoon")).thenReturn(Optional.of(categoryExpected));
        when(categoryMapper.castEntityToDTO(categoryExpected)).thenReturn(categoryDTOExpected);
        //Then
        CategoryDTO categoryDTOActual = categoryService.findByName("Cartoon");
        assertEquals(categoryDTOExpected,categoryDTOActual);
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
        Category categoryActual = new Category();
        categoryActual.setName("Cartoon");
        categoryActual.setId(1L);
        CategoryDTO categoryDTOActual = CategoryDTO.builder().name("Cartoon").build();
        String actual = "Add Complete";
        //When
        when(categoryMapper.castDTOToEntity(categoryDTOActual)).thenReturn(categoryActual);
        when(categoryRepository.save(any(Category.class))).thenReturn(categoryActual);
        //Then
        String expected = categoryService.addCategory(categoryDTOActual);
        verify(categoryRepository).save(categoryActual);
        assertEquals(actual,expected);
    }

    @Test
    void addCategoryListTest() {
        //Given
        Category categoryActual1 = new Category();
        categoryActual1.setName("Cartoon");
        categoryActual1.setId(1L);
        Category categoryActual2 = new Category();
        categoryActual2.setName("Action");
        categoryActual2.setId(1L);

        List<CategoryDTO> listActual = new ArrayList<>();
        listActual.add(categoryDTO1);
        listActual.add(categoryDTO2);
        //When
        when(categoryMapper.castDTOToEntity(categoryDTO1)).thenReturn(categoryActual1);
        when(categoryMapper.castDTOToEntity(categoryDTO2)).thenReturn(categoryActual2);
        when(categoryRepository.save(categoryActual1)).thenReturn(categoryActual1);
        when(categoryRepository.save(categoryActual2)).thenReturn(categoryActual2);
        //Then
        String actual = "Add List Complete";
        String expected = categoryService.addCategoryList(listActual);
        verify(categoryRepository).save(categoryActual1);
        verify(categoryRepository).save(categoryActual2);
        assertEquals(actual,expected);

    }

    @Test
    void findAllTest() {
        //Given
        List<CategoryDTO> listDTOActual = new ArrayList<>();
        listDTOActual.add(categoryDTO1);
        listDTOActual.add(categoryDTO2);

        List<Category> listActual = new ArrayList<>();
        listActual.add(category1);
        listActual.add(category2);

        //When
        when(categoryRepository.findAll()).thenReturn(listActual);
        when(categoryMapper.castListEntityToDTO(listActual)).thenReturn(listDTOActual);
        //Then
        List<CategoryDTO> listDTOExpected = categoryService.findAll();
        assertEquals(listDTOActual, listDTOExpected);
    }

    @Test
    void editCategoryTest() {
        Category categoryActual = new Category();
        categoryActual.setName("Cartoon");
        categoryActual.setId(1L);
        Category categoryActualEdit = new Category();
        categoryActualEdit.setId(1L);
        categoryActualEdit.setName("Action");
        CategoryDTO categoryDTOActual = CategoryDTO.builder().name("Action").build();
        String actual = "Edit Complete";
        //When
        when(categoryMapper.castDTOToEntity(categoryDTOActual)).thenReturn(categoryActualEdit);
        when(categoryRepository.saveAndFlush(categoryActualEdit)).thenReturn(categoryActualEdit);
        when(categoryRepository.findByName("Action")).thenReturn(Optional.of(categoryActualEdit));
        //Then
        String expected = categoryService.editCategory(categoryDTOActual);
        verify(categoryRepository).saveAndFlush(categoryActualEdit);
        assertEquals(actual,expected);
        Category categoryExpected = categoryRepository.findByName("Action").get();
        assertEquals(categoryActualEdit,categoryExpected);
    }

}
