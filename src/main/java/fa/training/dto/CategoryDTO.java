package fa.training.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

}
//    @ConstructorProperties({"name"})
//    CategoryDTO(String name){
//        this.name = name;
//    }
//    public static CategoryDTO.CategoryDTOBuilder builder() {
//        return new CategoryDTO.CategoryDTOBuilder();
//    }
//
//    public static class CategoryDTOBuilder {
//        private String name;
//
//        CategoryDTOBuilder() {
//        }
//
//        public CategoryDTO.CategoryDTOBuilder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public CategoryDTO build() {
//            return new CategoryDTO(this.name);
//        }
//    }
//
//}
