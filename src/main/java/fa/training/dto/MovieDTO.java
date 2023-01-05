package fa.training.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull()
    @Positive
    private int lengthMinute;
    @NotNull()
    @Positive()
    private double rating;
    private CategoryDTO categoryDTO;
//    @ConstructorProperties({"name", "description", "lengthMinute", "rating","categoryDTO"})
//    MovieDTO(String name,String description,int lengthMinute, double rating, CategoryDTO categoryDTO){
//        this.name = name;
//        this.description = description;
//        this.lengthMinute = lengthMinute;
//        this.rating = rating;
//        this.categoryDTO = categoryDTO;
//    }
//
//    public static  MovieDTO.MovieDTOBuilder builder() {
//        return new MovieDTO.MovieDTOBuilder();
//    }
//
//    public static class MovieDTOBuilder {
//        private String name;
//        private String description;
//        private int lengthMinute;
//        private double rating;
//        private CategoryDTO categoryDTO;
//
//        MovieDTOBuilder(){
//        }
//        public MovieDTO.MovieDTOBuilder name(String name) {
//            this.name = name;
//            return this;
//        }
//        public MovieDTO.MovieDTOBuilder description(String description) {
//            this.description = description;
//            return this;
//        }
//        public MovieDTO.MovieDTOBuilder rating(double rating) {
//            this.rating = rating;
//            return this;
//        }
//        public MovieDTO.MovieDTOBuilder lengthMinute(int lengthMinute) {
//            this.lengthMinute = lengthMinute;
//            return this;
//        }
//        public MovieDTO.MovieDTOBuilder categoryDTO(CategoryDTO categoryDTO) {
//            this.categoryDTO = categoryDTO;
//            return this;
//        }
//        public MovieDTO build() {
//            return new MovieDTO(this.name,this.description,this.lengthMinute, this.rating, this.categoryDTO);
//        }
//    }
//
//
//

}
