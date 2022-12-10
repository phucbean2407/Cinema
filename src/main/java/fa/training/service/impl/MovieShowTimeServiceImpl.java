package fa.training.service.impl;

import fa.training.dto.MovieShowTimeDTO;
import fa.training.dto.SeatDTO;
import fa.training.entity.MovieShowTime;
import fa.training.repository.*;
import fa.training.service.*;
import fa.training.service.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

;

@Service
public class MovieShowTimeServiceImpl  implements MovieShowTimeService {
    @Autowired
    private MovieShowTimeRepository movieShowTimeRepository;
    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HallService hallService;

    @Override
    public ResponseEntity<MovieShowTimeDTO> addMovieShowTime(MovieShowTimeDTO movieShowTimeDTO) {
        MovieShowTime movieShowTime = this.castDTOToEntity(movieShowTimeDTO);
        try{
            movieShowTimeRepository.save(movieShowTime);
            MovieShowTime movieShowTime1 =movieShowTimeRepository.
                    findForTicket(DateTimeUtils.fromDateToString(movieShowTimeDTO.getDate()),movieShowTimeDTO.getMovieDTO().getName(),
                    movieShowTimeDTO.getHallDTO().getName(),movieShowTimeDTO.getTime().getId());
            MovieShowTimeDTO movieShowTimeDto = this.castEntityToDTO(movieShowTime1);
            return new ResponseEntity<>(movieShowTimeDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }


    //kiểm tra số ghế còn lại của lịch chiếu.
    @Override
    public ResponseEntity<List<MovieShowTimeDTO>> findAllAndFreeSeats() {
        List<MovieShowTime> movieShowTimes = movieShowTimeRepository.findAll();
        List<MovieShowTimeDTO> movieShowTimeDTOS = this.castListEntityToDTO(movieShowTimes);
           return new ResponseEntity<>(movieShowTimeDTOS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MovieShowTimeDTO>> findByDateAndFreeSeats(String date) {
        List<MovieShowTime> movieShowTimes = movieShowTimeRepository.findByDate(date);
        List<MovieShowTimeDTO> movieShowTimeDTOS = this.castListEntityToDTO(movieShowTimes);
        return new ResponseEntity<>(movieShowTimeDTOS,HttpStatus.OK);
    }

    @Override
    public MovieShowTimeDTO castEntityToDTO(MovieShowTime movieShowTime) {
        MovieShowTimeDTO movieShowTimeDTO = new MovieShowTimeDTO();
        movieShowTimeDTO.setDate(movieShowTime.getDate());
        movieShowTimeDTO.setMovieDTO(movieService.castEntityToDTO(movieRepository.findByName(movieShowTime.getMovie().getName())));
        movieShowTimeDTO.setHallDTO(hallService.findByName(movieShowTime.getHall().getName()).getBody());
        movieShowTimeDTO.setTime(movieShowTime.getTime());
        Set<SeatDTO>  seatDTOS = movieShowTime.getSeats().stream()
                .map(seat -> {
                    SeatDTO seatDTO = new SeatDTO();
                    seatDTO.setName(seat.getName());
                    return seatDTO;
                }).collect(Collectors.toSet());
        movieShowTimeDTO.setSeatDTOS(seatDTOS);
        return movieShowTimeDTO;
    }

    @Override
    public List<MovieShowTimeDTO> castListEntityToDTO(List<MovieShowTime> movieShowTimes) {
        List<MovieShowTimeDTO> movieShowTimeDTOS = new ArrayList<>();
        for(MovieShowTime movieShowTime : movieShowTimes) {
            MovieShowTimeDTO movieShowTimeDTO = this.castEntityToDTO(movieShowTime);
            movieShowTimeDTOS.add(movieShowTimeDTO);
        }
        return movieShowTimeDTOS;
    }

    @Override
    public MovieShowTime castDTOToEntity(MovieShowTimeDTO movieShowTimeDTO) {
        MovieShowTime movieShowTime = new MovieShowTime();
        movieShowTime.setDate(movieShowTimeDTO.getDate());
        movieShowTime.setTime(movieShowTimeDTO.getTime());
        movieShowTime.setMovie(movieRepository.findByName(movieShowTimeDTO.getMovieDTO().getName()));
        movieShowTime.setHall(hallRepository.findByName(movieShowTimeDTO.getHallDTO().getName()));
        return movieShowTime;
    }
}
