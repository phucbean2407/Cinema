package fa.training.service.impl;


import fa.training.dto.CategoryDTO;
import fa.training.entity.Category;

import fa.training.repository.CategoryRepository;
import fa.training.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryDTO) {
        //Convert DTO to Entity
        Category category = this.castDTOToEntity(categoryDTO);
        try{
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
        } catch (Exception ex){
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
        return new ResponseEntity<>(this.castEntityToDTO(categoryRepository.findByName(name)),
                HttpStatus.OK);
    }

    @Override
    public CategoryDTO castEntityToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
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
        if(categoryRepository.findByName(categoryDTO.getName()) != null) {
             category = categoryRepository.findByName(categoryDTO.getName());
        }
        category.setName(categoryDTO.getName());
        return category;
    }


}
