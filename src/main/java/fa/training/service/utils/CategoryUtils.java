package fa.training.service.utils;

import fa.training.dto.CategoryDTO;
import fa.training.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryUtils {


    public static CategoryDTO castEntityToDTO(Category category) {
        return  CategoryDTO.builder()
                .name(category.getName())
                .build();
    }

    public static List<CategoryDTO> castListEntityToDTO(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category : categories){
            CategoryDTO categoryDTO = castEntityToDTO(category);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    public static Category castDTOToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }





}
