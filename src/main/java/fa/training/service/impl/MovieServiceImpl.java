package fa.training.service.impl;

import fa.training.dto.MovieDTO;
import fa.training.entity.Category;
import fa.training.entity.Movie;
import fa.training.mapper.MovieMapper;
import fa.training.repository.CategoryRepository;
import fa.training.repository.MovieRepository;
import fa.training.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final MovieMapper movieMapper;
    public MovieServiceImpl(MovieRepository movieRepository, CategoryRepository categoryRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.movieMapper = movieMapper;
    }
    @Override
    public String addMovie(MovieDTO movieDTO) {
        Movie movie = movieMapper.castDTOToEntity(movieDTO);
            movieRepository.save(movie);
            return "Add Complete";
    }
    @Override
    public String addMovieFromExcel(MultipartFile file) throws Exception {
        List<Movie> movies = movieMapper.castListMultipartFileToListEntity(file);
        movieRepository.saveAll(movies);
        return "Add List Complete";
    }
    @Override
    public Boolean deleteMovieByName(String name) {
        Optional<Movie> opt = movieRepository.findByName(name);
        if(opt.isPresent()) {
            Movie movie = opt.get();
            movieRepository.deleteById(movie.getId());
            return true;
        } else{
            return false;
        }
    }
    @Override
    public String editMovie(MovieDTO movieDTO) {
        Movie movie = movieMapper.castDTOToEntity(movieDTO);
            movieRepository.saveAndFlush(movie);
            return "Edit Complete";
    }
    @Override
    public List<MovieDTO> findAllFMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movieMapper.castListEntityToDTO(movies);
    }
    @Override
    public List<MovieDTO> findNeededMovies(String name) {
        List<Movie> movies = movieRepository.findNeededMovies(name);
        return movieMapper.castListEntityToDTO(movies);
    }
    @Override
    public List<MovieDTO> findBestMovie() {
        List<Movie> movies = movieRepository.findBestMovie();
        return movieMapper.castListEntityToDTO(movies);
    }
    @Override
    public List<MovieDTO> findByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow();
        List<Movie> movies = movieRepository.findByCategory(category);
        return movieMapper.castListEntityToDTO(movies);
    }
}
