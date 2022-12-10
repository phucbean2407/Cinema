package fa.training.service;

import fa.training.dto.MovieShowTimeDTO;
import fa.training.entity.MovieShowTime;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface MovieShowTimeService {

    ResponseEntity<MovieShowTimeDTO> addMovieShowTime(MovieShowTimeDTO movieShowTimeDTO) throws ParseException;

    ResponseEntity<List<MovieShowTimeDTO>> findAllAndFreeSeats();
    ResponseEntity<List<MovieShowTimeDTO>> findByDateAndFreeSeats(String date);

    MovieShowTimeDTO castEntityToDTO(MovieShowTime movieShowTime);
    List<MovieShowTimeDTO> castListEntityToDTO(List<MovieShowTime> movieShowTimes);
    MovieShowTime castDTOToEntity(MovieShowTimeDTO movieShowTimeDTO) throws ParseException;
}
