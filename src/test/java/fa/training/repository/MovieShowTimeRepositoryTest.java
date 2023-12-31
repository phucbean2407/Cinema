package fa.training.repository;

import fa.training.entity.MovieShowTime;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

public class MovieShowTimeRepositoryTest {

    @InjectMocks
    MovieShowTimeRepository movieShowTimeRepository;


    private MovieShowTime movieShowTime;
    private List<MovieShowTime> movieShowTimes;
    String date, movieName, hallName, timeId;

    @BeforeEach
    void setUp(){
        movieShowTime = new MovieShowTime();
        movieShowTimes = new ArrayList<>();
    }
}
