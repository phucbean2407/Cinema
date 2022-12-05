package fa.training.service.impl;

;
import fa.training.dto.MovieShowTimeDTO;
import fa.training.entity.MovieShowTime;
import fa.training.entity.TheaterHall;
import fa.training.repository.*;
import fa.training.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieShowTimeServiceImpl  implements MovieShowTimeService {
    @Autowired
    MovieShowTimeRepository movieShowTimeRepository;
    @Autowired
    TheaterHallRepository theaterHallRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    SeatService seatService;
    @Autowired
    MovieService movieService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TheaterHallService theaterHallService;
    @Autowired
    TimeRepository timeRepository;
    @Override
    public ResponseEntity<MovieShowTimeDTO> addMovieShowTime(MovieShowTimeDTO movieShowTimeDTO) {
        MovieShowTime movieShowTime = this.castDTOToEntity(movieShowTimeDTO);
        TheaterHall theaterHall = movieShowTime.getTheaterHall();
        theaterHallService.setHallReady(theaterHall.getName());
        try{
            theaterHallRepository.saveAndFlush(theaterHall);
            movieShowTimeRepository.save(movieShowTime);
            MovieShowTimeDTO movieShowTimeDto = this.castEntityToDTO(movieShowTimeRepository.findForTicket(movieShowTimeDTO.getDate(),movieShowTimeDTO.getMovieDTO().getName(),
                    movieShowTimeDTO.getTheaterHallDTO().getName(),movieShowTimeDTO.getTime().getTime()));
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
        movieShowTimeDTO.setTime(movieShowTime.getTime());
        movieShowTimeDTO.setMovieDTO(movieService.castEntityToDTO(movieRepository.findByName(movieShowTime.getMovie().getName())));
        movieShowTimeDTO.setTheaterHallDTO(theaterHallService.findByName(movieShowTime.getTheaterHall().getName()).getBody());
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
        if(movieShowTimeRepository.findForTicket(movieShowTimeDTO.getDate(),movieShowTimeDTO.getMovieDTO().getName(),
                movieShowTimeDTO.getTheaterHallDTO().getName(),movieShowTimeDTO.getTime().getTime()) !=null) {
            movieShowTime = movieShowTimeRepository.findForTicket(movieShowTimeDTO.getDate(),movieShowTimeDTO.getMovieDTO().getName(),
                    movieShowTimeDTO.getTheaterHallDTO().getName(),movieShowTimeDTO.getTime().getTime());
        }
        movieShowTime.setDate(movieShowTimeDTO.getDate());
        movieShowTime.setTime(timeRepository.findById(movieShowTimeDTO.getTime().getId()).get());
        movieShowTime.setMovie(movieRepository.findByName(movieShowTimeDTO.getMovieDTO().getName()));
        movieShowTime.setTheaterHall(theaterHallRepository.findByName(movieShowTimeDTO.getTheaterHallDTO().getName()));
        return movieShowTime;
    }
}
