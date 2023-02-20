package fa.training.controller;


import fa.training.dto.MovieDTO;
import fa.training.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add_movie")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.addMovie(movieDTO));
    }

    @PostMapping("/from-list")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(movieService.addMovieFromExcel(file));
    }


    @DeleteMapping("/del_movie")
    public ResponseEntity<?> deleteMovie(@RequestParam("name") String name)throws NullPointerException{
        if(Boolean.TRUE.equals(movieService.deleteMovieByName(name))){
            return ResponseEntity.ok("Complete!");
        } else {
            return ResponseEntity.ok("Can not delete");
        }
    }

    @PostMapping("/edit_movie")
    public ResponseEntity<?> editProduct(@Valid @RequestBody MovieDTO movieDTO)throws NullPointerException{
        return ResponseEntity.ok(movieService.editMovie(movieDTO));
    }
    @GetMapping("/get_movie")
    public ResponseEntity<List<MovieDTO>> getMovie(@RequestParam("name") String name)throws NullPointerException{
       return ResponseEntity.ok(movieService.findNeededMovies(name));
    }
    @GetMapping("/get-movie-by-category")
    public ResponseEntity<List<MovieDTO>> getMovieByCategory(@RequestParam("name") String categoryName)throws NullPointerException{
        return ResponseEntity.ok(movieService.findByCategory(categoryName));
    }

    @GetMapping("")
    public ResponseEntity<List<MovieDTO>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAllFMovies());
    }

}
