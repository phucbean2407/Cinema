package fa.training.controller;


import fa.training.dto.MovieDTO;
import fa.training.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add_movie")
    public ResponseEntity<MovieDTO> addMovie(@Valid @RequestBody MovieDTO movieDTO)throws NullPointerException{
        return movieService.addMovie(movieDTO);
    }

    @PostMapping("/add_movie_list")
    public ResponseEntity<List<MovieDTO>> addMovieFromList(@RequestBody List<MovieDTO> movieDTOS)throws NullPointerException{
        return movieService.addMovieFromList(movieDTOS);
    }



    @DeleteMapping("/del_movie")
    public String deleteMovie(@RequestParam("name") String name)throws NullPointerException{
        if(Boolean.TRUE.equals(movieService.deleteMovieByName(name).getBody())){
            return "Complete!";
        } else {
            return "Can not delete";
        }
    }

    @PostMapping("/edit_movie")
    public ResponseEntity<MovieDTO> editProduct(@Valid @RequestBody MovieDTO movieDTO)throws NullPointerException{
        return movieService.editMovie(movieDTO);
    }
    @GetMapping("/get_movie")
    public ResponseEntity<List<MovieDTO>> getMovie(@RequestParam("name") String name)throws NullPointerException{
       return movieService.findNeededMovies(name);
    }
    @GetMapping("/get-movie-by-category")
    public ResponseEntity<List<MovieDTO>> getMovieByCategory(@RequestParam("name") String categoryName)throws NullPointerException{
        return movieService.findByCategory(categoryName);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getAllMovies(){
        return movieService.findAllFMovies();
    }

}
