package fa.training.service.impl;

import fa.training.dto.MovieShowTimeDTO;
import fa.training.dto.SeatDTO;
import fa.training.entity.MovieShowTime;
import fa.training.repository.HallRepository;
import fa.training.repository.MovieRepository;
import fa.training.repository.MovieShowTimeRepository;
import fa.training.service.CategoryService;
import fa.training.service.HallService;
import fa.training.service.MovieService;
import fa.training.service.MovieShowTimeService;
import fa.training.service.utils.DateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

;

@Service
public class MovieShowTimeServiceImpl  implements MovieShowTimeService {
    private final MovieShowTimeRepository movieShowTimeRepository;
    private final HallRepository hallRepository;

    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final HallService hallService;

    public MovieShowTimeServiceImpl(MovieShowTimeRepository movieShowTimeRepository, HallRepository hallRepository, MovieService movieService, MovieRepository movieRepository, CategoryService categoryService, HallService hallService) {
        this.movieShowTimeRepository = movieShowTimeRepository;
        this.hallRepository = hallRepository;
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.hallService = hallService;
    }

    @Override
    public ResponseEntity<MovieShowTimeDTO> addMovieShowTime(MovieShowTimeDTO movieShowTimeDTO) {
        MovieShowTime movieShowTime = this.castDTOToEntity(movieShowTimeDTO);
        try{
            movieShowTimeRepository.save(movieShowTime);
            MovieShowTime movieShowTime1 =movieShowTimeRepository.
                    findForTicket(DateTimeUtils.fromDateToString(movieShowTimeDTO.getDate()),movieShowTimeDTO.getMovieDTO().getName(),
                    movieShowTimeDTO.getHallDTO().getName(),movieShowTimeDTO.getTime().getId())
                    .orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
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
        MovieShowTimeDTO movieShowTimeDTO = MovieShowTimeDTO.builder().build();
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
