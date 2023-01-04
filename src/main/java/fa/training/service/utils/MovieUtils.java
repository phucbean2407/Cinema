package fa.training.service.utils;

import fa.training.dto.CategoryDTO;
import fa.training.dto.MovieDTO;
import fa.training.entity.Category;
import fa.training.entity.Movie;
import fa.training.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
@Component
public class MovieUtils {
    static CategoryRepository categoryRepository;
    public static MovieDTO castEntityToDTO(Movie movie) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name(movie.getCategory().getName())
                .build();
        return MovieDTO.builder()
                .name(movie.getName())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .lengthMinute(movie.getLengthMinute())
                .categoryDTO(categoryDTO).build();
    }

    public static List<MovieDTO> castListEntityToDTO(List<Movie> movies) {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            MovieDTO movieDTO = castEntityToDTO(movie);
            movieDTOS.add(movieDTO);
        }
        return movieDTOS;
    }

    public static Movie castDTOToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setRating(movieDTO.getRating());
        movie.setLengthMinute(movieDTO.getLengthMinute());
        Category category = categoryRepository.findByName(movieDTO.getCategoryDTO().getName())
                .orElseThrow(() -> new NoSuchElementException("Not found Category."));
        movie.setCategory(category);
        return movie;
    }
}
