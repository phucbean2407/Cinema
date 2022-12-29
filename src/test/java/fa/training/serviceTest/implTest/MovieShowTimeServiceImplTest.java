package fa.training.serviceTest.implTest;

import fa.training.dto.CategoryDTO;
import fa.training.dto.HallDTO;
import fa.training.dto.MovieDTO;
import fa.training.dto.MovieShowTimeDTO;
import fa.training.entity.*;
import fa.training.repository.HallRepository;
import fa.training.repository.MovieRepository;
import fa.training.repository.MovieShowTimeRepository;
import fa.training.service.CategoryService;
import fa.training.service.MovieService;
import fa.training.service.impl.HallServiceImpl;
import fa.training.service.impl.MovieShowTimeServiceImpl;
import fa.training.service.utils.DateTimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MovieShowTimeServiceImplTest {

    @InjectMocks
    private MovieShowTimeServiceImpl movieShowTimeService;
    @Mock
    private MovieShowTimeRepository movieShowTimeRepository;
    @Mock
    private HallRepository hallRepository;


    @Mock
    private MovieService movieService;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private HallServiceImpl hallService;


    private MovieShowTime movieShowTime;
    private Time time;
    private Hall hall;
    private Date date;
    private Movie movie;
    private Category category;
    private MovieShowTimeDTO movieShowTimeDTO;
    private MovieShowTimeDTO movieShowTimeInsert;


    private List<MovieShowTime> movieShowTimes;
    private List<MovieShowTimeDTO> movieShowTimeDTOS;
    @BeforeEach
    void setUp(){
        movieShowTime  = new MovieShowTime();
        movieShowTimeDTO = MovieShowTimeDTO.builder().build();
        time = new Time();
        time.setId(1L);
        time.setTime("08:00");
        hall = new Hall();
        hall.setId(1L);
        hall.setName("A");
        movie = new Movie();
        category = new Category();
        category.setName("Cartoon");
        category.setId(1L);
        movie.setName("Doraemon");
        movie.setId(1);
        movie.setCategory(category);
        movie.setRating(8.9);
        movie.setDescription("AABBCC");
        movie.setLengthMinute(180);
        date = DateTimeUtils.fromStringToDate("2022-12-15");
        movieShowTime.setDate(date);
        movieShowTime.setMovie(movie);
        movieShowTime.setHall(hall);
        movieShowTime.setTime(time);

        movieShowTimes = new ArrayList<>();
        movieShowTimes.add(movieShowTime);
        movieShowTimeDTOS = new ArrayList<>();

    }

    @Test
    void findAllAndFreeSeatsTest() {
//        MovieShowTimeDTO movieShowTimeDTO = new MovieShowTimeDTO();
//        movieShowTimeDTO.setDate(date);
//        movieShowTimeDTO.setTime(time);
//        HallDTO hallDTO = new HallDTO();
//        hallDTO.setName("A");
//        movieShowTimeDTO.setHallDTO(hallDTO);
//        MovieDTO movieDTO = new MovieDTO();
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setName(category.getName());
//        movieDTO.setCategoryDTO(categoryDTO);
//        movieDTO.setName(movie.getName());
//        movieDTO.setRating(movie.getRating());
//        movieDTO.setDescription(movie.getDescription());
//        movieDTO.setLengthMinute(movie.getLengthMinute());
//        movieShowTimeDTO.setMovieDTO(movieDTO);
//        movieShowTimeDTO.setSeatDTOS(null);
//        movieShowTimeDTOS.add(movieShowTimeDTO);

        //when
        when(movieShowTimeRepository.findAll()).thenReturn(movieShowTimes);
        //then
        movieShowTimeDTOS = movieShowTimeService.castListEntityToDTO(movieShowTimes);
        List<MovieShowTimeDTO> findedMovieShowTimeDTOS = movieShowTimeService.findAllAndFreeSeats().getBody();
        assertEquals(movieShowTimeDTOS,findedMovieShowTimeDTOS);

    }


    @Test
    void addMovieShowTimeTest() {
         //Given
        movieShowTimeInsert = MovieShowTimeDTO.builder().build();
        CategoryDTO categoryInsert = CategoryDTO.builder().build();
        categoryInsert.setName(category.getName());
        MovieDTO movieDTO = MovieDTO.builder().build();
        movieDTO.setCategoryDTO(categoryInsert);
        movieDTO.setName(movie.getName());
        movieDTO.setRating(movie.getRating());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setLengthMinute(movie.getLengthMinute());
        movieShowTimeInsert.setMovieDTO(movieDTO);
        HallDTO hallDTO = HallDTO.builder().build();
        hallDTO.setName(hall.getName());
        movieShowTimeInsert.setHallDTO(hallDTO);
        movieShowTimeInsert.setDate(date);
        movieShowTimeInsert.setTime(time);

        //When
        when(movieShowTimeRepository.save(any(MovieShowTime.class))).then(new Answer<MovieShowTime>() {
            int sequence = 2;
            @Override
            public MovieShowTime answer(InvocationOnMock invocation) throws Throwable {
                MovieShowTime movieShowTime = (MovieShowTime) invocation.getArgument(0);
                movieShowTime.setId(sequence++);
                return movieShowTime;
            }
        });
        //Then
        MovieShowTimeDTO movieShowTimeInserted = movieShowTimeService.addMovieShowTime(movieShowTimeInsert).getBody();
        verify(movieShowTimeRepository).save(movieShowTime);
        assertEquals(movieShowTimeInsert,movieShowTimeInserted);

    }
}


