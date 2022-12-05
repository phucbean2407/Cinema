package fa.training.service;

import fa.training.dto.CategoryDTO;
import fa.training.entity.Category;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface CategoryService {
  ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryDTO);
  ResponseEntity<List<CategoryDTO>> addCategoryList(List<CategoryDTO> categoryDTO);
  ResponseEntity<List<CategoryDTO>> findAll();
  ResponseEntity<Boolean> deleteCategory(long categoryId);
  ResponseEntity<CategoryDTO> editCategory(CategoryDTO categoryDTO);
  ResponseEntity<CategoryDTO> findByName(String name);

  CategoryDTO castEntityToDTO(Category category);
  List<CategoryDTO> castListEntityToDTO(List<Category> categories);
  Category castDTOToEntity(CategoryDTO categoryDTO);

}
