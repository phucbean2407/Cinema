package fa.training.serviceTest.implTest;

import fa.training.dto.CategoryDTO;
import fa.training.dto.HallDTO;
import fa.training.dto.MovieDTO;
import fa.training.dto.MovieShowTimeDTO;
import fa.training.entity.*;
import fa.training.repository.HallRepository;
import fa.training.repository.MovieRepository;
import fa.training.repository.MovieShowTimeRepository;
import fa.training.service.impl.HallServiceImpl;
import fa.training.service.impl.MovieServiceImpl;
import fa.training.service.impl.MovieShowTimeServiceImpl;
import fa.training.service.utils.DateTimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class MovieShowTimeServiceImplTest {

    @InjectMocks
    private MovieShowTimeServiceImpl movieShowTimeService;
    @Mock
    private MovieShowTimeRepository movieShowTimeRepository;
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

        movieShowTime  = new MovieShowTime();

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
        movieShowTime.setDate(date);
        movieShowTime.setMovie(movie);
        movieShowTime.setHall(hall);
        movieShowTime.setTime(time);
        movieShowTimes = new ArrayList<>();
        movieShowTimes.add(movieShowTime);

        hallDTO = HallDTO.builder().name("A").build();

        //categoryDTO = CategoryDTO.builder().name("Cartoon").build();
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

//    @Test
//    void findAllAndFreeSeatsTest() {
//
//        //When
//        when(movieRepository.findByName(movieShowTimeDTO.getMovieDTO().getName())).thenReturn(Optional.of(movie));
//        when(movieService.castEntityToDTO(movie)).thenReturn(movieDTO);
//        when(hallService.findByName(movieShowTimeDTO.getHallDTO().getName())).thenReturn(ResponseEntity.ok(hallDTO));
//        when(movieShowTimeRepository.findAll()).thenReturn(movieShowTimes);
//        //Then
//        movieShowTimeDTOCast = movieShowTimeService.castListEntityToDTO(movieShowTimes);
//        List<MovieShowTimeDTO> movieShowTimeDTOS2 = movieShowTimeService.findAllAndFreeSeats().getBody();
//        assert movieShowTimeDTOS2 != null;
//        assertEquals(movieShowTimeDTOCast.size(),movieShowTimeDTOS2.size());
//        assertEquals(movieShowTimeDTOCast.get(0).getTime(), movieShowTimeDTOS2.get(0).getTime());
//        assertEquals(movieShowTimeDTOCast.get(0).getMovieDTO(), movieShowTimeDTOS2.get(0).getMovieDTO());
//        assertEquals(movieShowTimeDTOCast.get(0).getDate(), movieShowTimeDTOS2.get(0).getDate());
//        assertEquals(movieShowTimeDTOCast.get(0).getHallDTO(), movieShowTimeDTOS2.get(0).getHallDTO());
//    }
//
//    @Test
//    void addMovieShowTimeTest() {
//        //Given
//        MovieShowTimeDTO movieShowTimeInsert = movieShowTimeDTO;
//
//        //When
//        when(movieService.castEntityToDTO(movie)).thenReturn(movieDTO);
//        when(hallService.findByName(movieShowTimeDTO.getHallDTO().getName())).thenReturn(ResponseEntity.ok(hallDTO));
//        when(movieRepository.findByName(movieShowTimeDTO.getMovieDTO().getName())).thenReturn(Optional.of(movie));
//        when(hallRepository.findByName(movieShowTimeDTO.getHallDTO().getName())).thenReturn(Optional.of(hall));
//
//        when(movieShowTimeRepository.save(any(MovieShowTime.class))).then((Answer<MovieShowTime>) invocation -> {
//            MovieShowTime movieShowTime = invocation.getArgument(0);
//            movieShowTime.setDate(date);
//            movieShowTime.setMovie(movie);
//            movieShowTime.setHall(hall);
//            movieShowTime.setTime(time);
//            return movieShowTime;
//        });
//        when(movieShowTimeRepository.findForTicket(DateTimeUtils.fromDateToString(movieShowTimeDTO.getDate())
//                        ,movieShowTimeDTO.getMovieDTO().getName(), movieShowTimeDTO.getHallDTO().getName(),
//                        movieShowTimeDTO.getTime().getId())).thenReturn(Optional.of(movieShowTime));
//        //Then
//        MovieShowTime showTimeCast  = movieShowTimeService.castDTOToEntity(movieShowTimeInsert);
//
//        MovieShowTimeDTO showTimeDTOCast = movieShowTimeService.castEntityToDTO(movieShowTime);
//        showTimeDTOCast.setSeatDTOS(null);
//        ResponseEntity<MovieShowTimeDTO> showTimes  = movieShowTimeService.addMovieShowTime(movieShowTimeInsert);
//     //   verify(movieShowTimeRepository).save(movieShowTime);
//        MovieShowTimeDTO mst = showTimes.getBody();
//        assert mst != null;
//        mst.setSeatDTOS(null);
//        movieShowTime.setSeats(null);
//        assertEquals(showTimeCast,movieShowTime);
//        assertEquals(mst.getHallDTO(),showTimeDTOCast.getHallDTO());
//        assertEquals(mst.getDate(),showTimeDTOCast.getDate());
//        assertEquals(mst.getHallDTO(),showTimeDTOCast.getHallDTO());
//        assertEquals(mst.getMovieDTO(),showTimeDTOCast.getMovieDTO());
//
//    }
}


