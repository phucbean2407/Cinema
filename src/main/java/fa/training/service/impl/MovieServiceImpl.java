package fa.training.service.impl;


import fa.training.dto.CategoryDTO;
import fa.training.dto.MovieDTO;
import fa.training.entity.Category;
import fa.training.entity.Movie;
import fa.training.repository.CategoryRepository;
import fa.training.repository.MovieRepository;
import fa.training.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<MovieDTO> addMovie(MovieDTO movieDTO) {
        Movie movie = castDTOToEntity(movieDTO);
        try {
            movieRepository.save(movie);
            return new ResponseEntity<>(movieDTO,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<List<MovieDTO>> addMovieFromList(List<MovieDTO> movieDTOS) {
        try {
            for(MovieDTO movieDTO : movieDTOS){
                Movie movie = castDTOToEntity(movieDTO);
                movieRepository.save(movie);
            }
            return new ResponseEntity<>(movieDTOS,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Boolean> deleteMovieByName(String name) {
        try{
            Movie movie = movieRepository.findByName(name);
            movieRepository.deleteById(movie.getId());
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MovieDTO> editMovie(MovieDTO movieDTO) {
        Movie movie = this.castDTOToEntity(movieDTO);
        try {
            movieRepository.saveAndFlush(movie);
            return new ResponseEntity<>(movieDTO,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<MovieDTO>> findAllFMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOS = castListEntityToDTO(movies);
        return new ResponseEntity<>(movieDTOS, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<MovieDTO>> findNeededMovies(String name) {
        List<Movie> movies = movieRepository.findNeededMovies(name);
        List<MovieDTO> movieDTOS = this.castListEntityToDTO(movies);
        return new ResponseEntity<>(movieDTOS, HttpStatus.OK);
    }
    //Find Best Movie: Rating >=8
    @Override
    public ResponseEntity<List<MovieDTO>> findBestMovie() {
        List<Movie> movies = movieRepository.findBestMovie();
        List<MovieDTO> movieDTOS = this.castListEntityToDTO(movies);
        return new ResponseEntity<>(movieDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MovieDTO>> findByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(() -> new RuntimeException(""));
        List<Movie> movies = movieRepository.findByCategory(category);
        List<MovieDTO> movieDTOS = this.castListEntityToDTO(movies);
        return new ResponseEntity<>(movieDTOS, HttpStatus.OK);
    }


    @Override
    public MovieDTO castEntityToDTO(Movie movie) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name(movie.getCategory().getName())
                .build();
        MovieDTO movieDTO = MovieDTO.builder()
                .name(movie.getName())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .lengthMinute(movie.getLengthMinute())
                .categoryDTO(categoryDTO).build();
        return movieDTO;
    }

    @Override
    public List<MovieDTO> castListEntityToDTO(List<Movie> movies) {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            MovieDTO movieDTO = castEntityToDTO(movie);
            movieDTOS.add(movieDTO);
        }
        return movieDTOS;
    }

    @Override
    public Movie castDTOToEntity(MovieDTO movieDTO) {
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
