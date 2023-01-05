package fa.training.controller;

import fa.training.dto.CategoryDTO;
import fa.training.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@RestController("/api")
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add_category")
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }
    @PostMapping("/add_category1")
    public ResponseEntity<String> addCategoryList(@Valid @RequestBody List<CategoryDTO> categoryDTOs) {
        return ResponseEntity.ok(categoryService.addCategoryList(categoryDTOs));
    }
    @DeleteMapping("/del_category")
    public ResponseEntity<String> deleteCategory(@RequestParam(value = "id") long categoryId) {
        if (Objects.equals(Boolean.TRUE, categoryService.deleteCategory(categoryId))) {
            return ResponseEntity.ok("Delete complete");
        } else {
            return ResponseEntity.ok("Can not find Category which is deleted");
        }
    }
    @PostMapping("/edit_category")
    public ResponseEntity<String> editCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.editCategory(categoryDTO));
    }
    //Code này ngu nhỉ :)))) Tự nhiên tìm tên có tên :)) Thôi sửa thành get có tồn tại k
    @GetMapping("/get_category")
    public ResponseEntity<CategoryDTO> getCategoryExist(@RequestParam("name") String name){
        return ResponseEntity.ok(categoryService.findByName(name));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return ResponseEntity.ok(categoryService.findAll());
    }
}


