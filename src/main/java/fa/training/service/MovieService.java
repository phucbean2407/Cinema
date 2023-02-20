package fa.training.service;


import fa.training.dto.MovieDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    String addMovie(MovieDTO movieDTO);

    String addMovieFromExcel(MultipartFile file) throws Exception;

    Boolean deleteMovieByName(String name);

    String editMovie(MovieDTO movieDTO);

    List<MovieDTO> findNeededMovies(String name); // Native Query (Select * From... WHERE like)

    List<MovieDTO> findAllFMovies();

    List<MovieDTO> findBestMovie();//Rating > 8

    List<MovieDTO> findByCategory(String categoryName);

}
