package fa.training.service;


import fa.training.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    String addMovie(MovieDTO movieDTO);

    String addMovieFromList(List<MovieDTO> movieDTOS);

    Boolean deleteMovieByName(String name);

    String editMovie(MovieDTO movieDTO);

    List<MovieDTO> findNeededMovies(String name); // Native Query (Select * From... WHERE like)

    List<MovieDTO> findAllFMovies();

    List<MovieDTO> findBestMovie();//Rating > 8

    List<MovieDTO> findByCategory(String categoryName);

}
