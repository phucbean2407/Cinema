package fa.training.service.impl;

import fa.training.dto.CategoryDTO;
import fa.training.dto.HallDTO;
import fa.training.dto.MovieDTO;
import fa.training.dto.MovieShowTimeDTO;
import fa.training.entity.*;
import fa.training.enumerates.Time;
import fa.training.mapper.MovieShowTimeMapper;
import fa.training.repository.MovieShowTimeRepository;
import fa.training.service.utils.DateTimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MovieShowTimeServiceImplTest {

    @InjectMocks
    private MovieShowTimeServiceImpl movieShowTimeService;
    @Mock
    private MovieShowTimeRepository movieShowTimeRepository;
    @Mock
    private MovieShowTimeMapper movieShowTimeMapper;

    private MovieShowTime movieShowTime;
    private MovieShowTimeDTO movieShowTimeDTO;
    private Time time;
    private Hall hall;
    private Date date;
    private Movie movie;
    private MovieDTO movieDTO;
    private HallDTO hallDTO;

    private List<MovieShowTimeDTO> movieShowTimeDTOCast;
    private List<MovieShowTime> movieShowTimes;

    @BeforeEach
    void setUp(){
        movieShowTimeDTOCast = new ArrayList<>();
//Date time constain private static Date/Time

        time = Time.FIFTH;
        hall = new Hall();
        hall.setId(1L);
        hall.setName("A");


        //Mock Category
        Category category = new Category();
        category.setName("Cartoon");
        category.setId(1L);

        //Mock Movie
        movie = new Movie();
        movie.setName("Doraemon");
        movie.setId(1);
        movie.setCategory(category);
        movie.setRating(8.9);
        movie.setDescription("AABBCC");
        movie.setLengthMinute(180);

        date = DateTimeUtils.fromStringToDate("2022-12-15");

        //Mock MovieShowTime
        movieShowTime  = new MovieShowTime();
        movieShowTime.setDate(date);
        movieShowTime.setMovie(movie);
        movieShowTime.setHall(hall);
        movieShowTime.setTime(time);
        movieShowTimes = new ArrayList<>();
        movieShowTimes.add(movieShowTime);

        hallDTO = HallDTO.builder().name("A").build();
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Cartoon").build();
        movieDTO = MovieDTO.builder()
                .name("Doraemon")
                .description("AABBCC")
                .lengthMinute(180)
                .categoryDTO(categoryDTO.getName())
                .rating(8.9)
                .build();

        //Mock List MovieShowTimeDTO
        List<MovieShowTimeDTO> movieShowTimeDTOS = new ArrayList<>();
        movieShowTimeDTO = MovieShowTimeDTO.builder()
                .time(time)
                .date(date)
                .hallDTO(hallDTO)
                .movieDTO(movieDTO)
                .build();
        movieShowTimeDTOS.add(movieShowTimeDTO);
    }

    @Test
    void addMovieShowTimeTest(){
        //Given
        String expected = "Add Complete";

        //When
        when(movieShowTimeMapper.castDTOToEntity(movieShowTimeDTO)).thenReturn(movieShowTime);
        when(movieShowTimeRepository.save(any(MovieShowTime.class))).thenReturn(movieShowTime);

        //Then
        String actual = movieShowTimeService.addMovieShowTime(movieShowTimeDTO);
        verify(movieShowTimeRepository).save(movieShowTime);
        assertEquals(actual, expected);
    }

    @Test
    void findAllAndFreeSeatsTest() {
        //Given
        MovieShowTimeDTO movieShowTimeDTOFound = movieShowTimeDTO;
        List<MovieShowTimeDTO> listMovieShowTimeExpected = List.of(movieShowTimeDTOFound);

        //When
        when(movieShowTimeRepository.findAll()).thenReturn(movieShowTimes);
        when(movieShowTimeMapper.castListEntityToDTO(movieShowTimes)).thenReturn(listMovieShowTimeExpected);
        //Then

        List<MovieShowTimeDTO> listMovieShowTimeActual = movieShowTimeService.findAllAndFreeSeats();
        assertEquals(listMovieShowTimeActual, listMovieShowTimeExpected);
    }

    @Test
    void findByDateAndFreeSeatsTest() {
        //Given
        List<MovieShowTimeDTO> listMovieShowTimeActual = new ArrayList<>();
        MovieShowTimeDTO movieShowTimeDTOFound = movieShowTimeDTO;
        listMovieShowTimeActual.add(movieShowTimeDTOFound);
        //When
        when(movieShowTimeRepository.findByDate(movieShowTime.getDate().toString())).thenReturn(movieShowTimes);
        when(movieShowTimeMapper.castListEntityToDTO(movieShowTimes)).thenReturn(listMovieShowTimeActual);
        //Then
        List<MovieShowTimeDTO> listMovieShowTimeExpected = movieShowTimeService.findByDateAndFreeSeats(movieShowTime.getDate().toString());
        assertEquals(listMovieShowTimeActual, listMovieShowTimeExpected);
    }

}


