package fa.training.service.impl;


import fa.training.dto.CategoryDTO;
import fa.training.entity.Category;
import fa.training.repository.CategoryRepository;
import fa.training.service.CategoryService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryDTO) {
        //Convert DTO to Entity
        Category category = this.castDTOToEntity(categoryDTO);
        try{
            //verify
            categoryRepository.save(category);
            return new ResponseEntity<>(categoryDTO,HttpStatus.CREATED);
        } catch (Exception ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> addCategoryList(List<CategoryDTO> categoryDTOs) {
        try{
            for(CategoryDTO categoryDTO: categoryDTOs){
                Category category = this.castDTOToEntity(categoryDTO);
                categoryRepository.save(category);
            }
            return new ResponseEntity<>(categoryDTOs,HttpStatus.CREATED);
        } catch (ConstraintViolationException ex){
            return new ResponseEntity("Category exists",HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> categories= categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = this.castListEntityToDTO(categories);
        return new ResponseEntity<>(categoryDTOS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteCategory(long categoryId) {
       try{
           categoryRepository.deleteById(categoryId);
           return new ResponseEntity<>(true,HttpStatus.OK);
       }catch (Exception e) {
           return new ResponseEntity<>(false,HttpStatus.OK);
       }
    }

    @Override
    public ResponseEntity<CategoryDTO> editCategory(CategoryDTO categoryDTO) {
        Category category= this.castDTOToEntity(categoryDTO);
        try{
            categoryRepository.saveAndFlush(category);
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<CategoryDTO> findByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
        CategoryDTO categoryDTO = castEntityToDTO(category);
        return new ResponseEntity<>(categoryDTO,
                HttpStatus.OK);
    }

    @Override
    public CategoryDTO castEntityToDTO(Category category) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name(category.getName())
                .build();
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> castListEntityToDTO(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category : categories){
            CategoryDTO categoryDTO = this.castEntityToDTO(category);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public Category castDTOToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }


}
