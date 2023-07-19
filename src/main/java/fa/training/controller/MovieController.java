package fa.training.controller;


import fa.training.dto.MovieDTO;
import fa.training.dto.ResourceDTO;
import fa.training.service.ExcelService;
import fa.training.service.MovieService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final ExcelService excelService;
    private final MovieService movieService;

    public MovieController(MovieService movieService, ExcelService excelService) {
        this.movieService = movieService;
        this.excelService = excelService;
    }

    @PostMapping("/add_movie")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.addMovie(movieDTO));
    }

    @PostMapping("/from-list")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(movieService.addMovieFromExcel(file));
    }


    @DeleteMapping("/del_movie")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @GetMapping("/export-movie")
    public ResponseEntity<Resource> exportUsers(){
        ResourceDTO resourceDTO= excelService.exportMovies();

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Content-Disposition",
                "attachment; filename="+"Movies.xlsx");

        return ResponseEntity.ok()
                .contentType(resourceDTO.getMediaType())
                .headers(httpHeaders)
                .body(resourceDTO.getResource());
    }
}
