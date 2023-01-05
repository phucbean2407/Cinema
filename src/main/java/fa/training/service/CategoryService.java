package fa.training.service;

import fa.training.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
  String addCategory(CategoryDTO categoryDTO);
  String addCategoryList(List<CategoryDTO> categoryDTO);
  List<CategoryDTO> findAll();
  Boolean deleteCategory(long categoryId);
  String editCategory(CategoryDTO categoryDTO);
  CategoryDTO findByName(String name);


}
