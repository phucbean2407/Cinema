package fa.training.serviceTest.implTest;

import fa.training.dto.CategoryDTO;
import fa.training.dto.HallDTO;
import fa.training.dto.MovieDTO;
import fa.training.dto.MovieShowTimeDTO;
import fa.training.entity.*;
import fa.training.mapper.MovieShowTimeMapper;
import fa.training.repository.HallRepository;
import fa.training.repository.MovieRepository;
import fa.training.repository.MovieShowTimeRepository;
import fa.training.service.impl.HallServiceImpl;
import fa.training.service.impl.MovieServiceImpl;
import fa.training.service.impl.MovieShowTimeServiceImpl;
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
    @Mock
    private HallRepository hallRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private MovieServiceImpl movieService;
    @Mock
    private HallServiceImpl hallService;

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


        time = new Time();
        time.setId(1L);
        time.setTime("08:00");
        hall = new Hall();
        hall.setId(1L);
        hall.setName("A");
        movie = new Movie();
        Category category = new Category();
        category.setName("Cartoon");
        category.setId(1L);
        movie.setName("Doraemon");
        movie.setId(1);
        movie.setCategory(category);
        movie.setRating(8.9);
        movie.setDescription("AABBCC");
        movie.setLengthMinute(180);
        date = DateTimeUtils.fromStringToDate("2022-12-15");
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
                .categoryDTO(categoryDTO)
                .rating(8.9)
                .build();
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
        String actual = "Add Complete";
        //When
        when(movieShowTimeMapper.castDTOToEntity(movieShowTimeDTO)).thenReturn(movieShowTime);
        when(movieShowTimeRepository.save(any(MovieShowTime.class))).thenReturn(movieShowTime);
        //Then
        String expected = movieShowTimeService.addMovieShowTime(movieShowTimeDTO);
        verify(movieShowTimeRepository).save(movieShowTime);
        assertEquals(actual,expected);
    }

    @Test
    void findAllAndFreeSeatsTest() {
        //Given
        List<MovieShowTimeDTO> listMovieShowTimeActual = new ArrayList<>();
        MovieShowTimeDTO movieShowTimeDTOFound = movieShowTimeDTO;
        listMovieShowTimeActual.add(movieShowTimeDTOFound);

        //When
        when(movieShowTimeRepository.findAll()).thenReturn(movieShowTimes);
        when(movieShowTimeMapper.castListEntityToDTO(movieShowTimes)).thenReturn(listMovieShowTimeActual);
        //Then

        List<MovieShowTimeDTO> listMovieShowTimeExpected = movieShowTimeService.findAllAndFreeSeats();
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


