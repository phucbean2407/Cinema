package fa.training.controller;

import fa.training.dto.MovieShowTimeDTO;
import fa.training.service.MovieShowTimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api/movieshowtimes")
public class MovieShowTimeController {
    private final MovieShowTimeService movieShowTimeService;

    public MovieShowTimeController(MovieShowTimeService movieShowTimeService) {
        this.movieShowTimeService = movieShowTimeService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_movie_show_time")
    public ResponseEntity<?> addMovieShowTime(@Valid @RequestBody MovieShowTimeDTO movieShowTimeDTO) throws ParseException {
        return ResponseEntity.ok(movieShowTimeService.addMovieShowTime(movieShowTimeDTO));
    }



    @GetMapping("/get_movie_show_time")
    public ResponseEntity<List<MovieShowTimeDTO>> findByDateAndFreeSeats(@RequestParam("date")String date){
        return ResponseEntity.ok(movieShowTimeService.findByDateAndFreeSeats(date));
    }
    @GetMapping("")
    public ResponseEntity<List<MovieShowTimeDTO>> getAllFindAllAndFreeSeats(){
        return ResponseEntity.ok(movieShowTimeService.findAllAndFreeSeats());
    }
}
