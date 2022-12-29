package fa.training.controller;

import fa.training.dto.MovieShowTimeDTO;
import fa.training.service.MovieShowTimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MovieShowTimeController {
    private final MovieShowTimeService movieShowTimeService;

    public MovieShowTimeController(MovieShowTimeService movieShowTimeService) {
        this.movieShowTimeService = movieShowTimeService;
    }


    @PostMapping("/add_movie_show_time")
    public ResponseEntity<MovieShowTimeDTO> addMovieShowTime(@Valid @RequestBody MovieShowTimeDTO movieShowTimeDTO) throws ParseException {
        return movieShowTimeService.addMovieShowTime(movieShowTimeDTO);
    }



    @GetMapping("/get_movie_show_time")
    public ResponseEntity<List<MovieShowTimeDTO>> findByDateAndFreeSeats(@RequestParam("date")String date){
        return movieShowTimeService.findByDateAndFreeSeats(date);
    }
    @GetMapping("/movie_show_times")
    public ResponseEntity<List<MovieShowTimeDTO>> getAllFindAllAndFreeSeats(){
        return movieShowTimeService.findAllAndFreeSeats();
    }
}
