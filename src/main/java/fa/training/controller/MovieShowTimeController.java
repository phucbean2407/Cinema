package fa.training.controller;

import fa.training.dto.MovieShowTimeDTO;


import fa.training.service.MovieShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MovieShowTimeController {
    @Autowired
    private MovieShowTimeService movieShowTimeService;


    @PostMapping("/add_movie_show_time)")
    public ResponseEntity<MovieShowTimeDTO> addMovieShowTime(@Valid @RequestBody MovieShowTimeDTO movieShowTimeDTO){
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
