package fa.training.service;


import fa.training.dto.MovieDTO;

import fa.training.entity.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    ResponseEntity<MovieDTO> addMovie(MovieDTO movieDTO);

    ResponseEntity<List<MovieDTO>> addMovieFromList(List<MovieDTO> movieDTOS);

    ResponseEntity<Boolean> deleteMovieByName(String name);

    ResponseEntity<MovieDTO> editMovie(MovieDTO movieDTO);

    ResponseEntity<List<MovieDTO>> findNeededMovies(String name); // Native Query (Select * From... WHERE like)

    ResponseEntity<List<MovieDTO>> findAllFMovies();

    ResponseEntity<List<MovieDTO>> findBestMovie();//Rating > 8

    ResponseEntity<List<MovieDTO>> findByCategory(String categoryName);

    MovieDTO castEntityToDTO(Movie movie);
    List<MovieDTO> castListEntityToDTO(List<Movie> movies);
    Movie castDTOToEntity(MovieDTO movieDTO);
}
