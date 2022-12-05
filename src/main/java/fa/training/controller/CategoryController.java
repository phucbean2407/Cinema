package fa.training.controller;

import fa.training.dto.CategoryDTO;


import fa.training.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController("/api")
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add_category")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);
    }
    @PostMapping("/add_category1")
    public ResponseEntity<List<CategoryDTO>> addCategoryList(@Valid @RequestBody List<CategoryDTO> categoryDTOs) {
        return categoryService.addCategoryList(categoryDTOs);
    }
    @DeleteMapping("/del_category")
    public String deleteCategory(@RequestParam(value = "id") long categoryId) {
        if (Boolean.TRUE.equals(categoryService.deleteCategory(categoryId).getBody())) {
            return "Complete";
        } else {
            return "Can not find Category which is deleted";
        }
    }
    @PostMapping("/edit_category")
    public ResponseEntity<CategoryDTO> editCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.editCategory(categoryDTO);
    }
    //Code này ngu nhỉ :)))) Tự nhiên tìm tên có tên :)) Thôi sửa thành get có tồn tại k
    @GetMapping("/get_category")
    public ResponseEntity<CategoryDTO> getCategoryExist(@RequestParam("name") String name){
        return categoryService.findByName(name);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return categoryService.findAll();
    }
}


