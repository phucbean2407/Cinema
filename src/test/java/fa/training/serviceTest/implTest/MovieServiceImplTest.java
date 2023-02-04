package fa.training.serviceTest.implTest;

import fa.training.dto.CategoryDTO;
import fa.training.dto.MovieDTO;
import fa.training.entity.Category;
import fa.training.entity.Movie;
import fa.training.mapper.MovieMapper;
import fa.training.repository.CategoryRepository;
import fa.training.repository.MovieRepository;
import fa.training.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private MovieMapper movieMapper;
    @InjectMocks
    private MovieServiceImpl movieService;

    private Category category;
    private CategoryDTO categoryDTO;
    private Movie movie;
    private MovieDTO movieDTO;
    private List<Movie> movies;

    private List<MovieDTO> movieDTOS;
    @BeforeEach
    void setUp() throws Exception{

        categoryDTO = CategoryDTO.builder()
                .name("Cartoon").build();

        category = new Category();
        category.setId(1);
        category.setName("Cartoon");

        movieDTO = MovieDTO.builder()
                .name("Doraemon")
                .description("AAABBBCCC")
                .rating(8.9)
                .lengthMinute(180)
                .categoryDTO(categoryDTO).build();

        movies = new ArrayList<>();

        movieDTOS = new ArrayList<>();


        movie = new Movie();
        movie.setName("Nobita's Little Star Wars 4");
        movie.setId(6L);
        movie.setCategory(category);
        movie.setRating(9.3);
        movie.setDescription("One day, Nobita picks a small rocket from which a small-sized humanoid alien Papi comes out.");
        movie.setLengthMinute(108);
        movies.add(movie);
    }
    @Test
    void addMovieTest(){
        //Given
        MovieDTO movieInsert = MovieDTO.builder()
                .categoryDTO(categoryDTO)
                .name("One Piece Film: Red")
                .rating(9.4)
                .description("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.")
                .lengthMinute(115).build();

        Movie movieCast = new Movie();
        Category categoryInsert = new Category();
        categoryInsert.setId(1L);
        categoryInsert.setName("Cartoon");
        movieCast.setCategory(categoryInsert);
        movieCast.setRating(9.4);
        movieCast.setName("One Piece Film: Red");
        movieCast.setDescription("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.");
        movieCast.setLengthMinute(115);

        Movie movie = new Movie();
        movie.setName("One Piece Film: Red");
        movie.setCategory(category);
        movie.setRating(9.4);
        movie.setDescription("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.");
        movie.setLengthMinute(115);
        String actual = "Add Complete";

        // When
        when(movieMapper.castDTOToEntity(movieInsert)).thenReturn(movieCast);
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        //Then
        assertEquals(movie,movieCast);
        String expected = movieService.addMovie(movieInsert);
        verify(movieRepository).save(movieCast);
        assertEquals(actual,expected);
    }

    @Test
    void addMovieFromList() {
        //Given
        MovieDTO movieInsert = MovieDTO.builder()
                .categoryDTO(categoryDTO)
                .name("One Piece Film: Red")
                .rating(9.4)
                .description("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.")
                .lengthMinute(115).build();

        Movie movie = new Movie();
        Category categoryInsert = new Category();
        movie.setName("One Piece Film: Red");
        movie.setCategory(category);
        movie.setRating(9.4);
        movie.setDescription("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.");
        movie.setLengthMinute(115);


        Movie movieCast = new Movie();
        categoryInsert.setId(1L);
        categoryInsert.setName("Cartoon");
        movieCast.setCategory(categoryInsert);
        movieCast.setRating(9.4);
        movieCast.setName("One Piece Film: Red");
        movieCast.setDescription("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.");
        movieCast.setLengthMinute(115);

        String actual = "Add List Complete";

        List<MovieDTO> listMovieDTO = new ArrayList<>();
        listMovieDTO.add(movieInsert);
        List<Movie> listMovie = new ArrayList<>();
        listMovie.add(movieCast);

        //When
        when(movieMapper.castDTOToEntity(movieInsert)).thenReturn(movieCast);
        when(movieRepository.save(movieCast)).thenReturn(movie);
        //Then
        String expected = movieService.addMovieFromList(listMovieDTO);
        verify(movieRepository).save(movieCast);
        assertEquals(actual,expected);
    }
    @Test
    void findNeededMoviesTest(){
        //Given
        MovieDTO movieFinded = MovieDTO.builder()
                .name("Nobita's Little Star Wars 4")
                .categoryDTO(categoryDTO)
                .rating(9.3)
                .description("One day, Nobita picks a small rocket from which a small-sized humanoid alien Papi comes out.")
                .lengthMinute(108).build();
        List<MovieDTO> listMovieCast = new ArrayList<>();
        listMovieCast.add(movieFinded);
        //When
        when(movieRepository.findNeededMovies(movie.getName())).thenReturn(movies);
        when(movieMapper.castListEntityToDTO(movies)).thenReturn(listMovieCast);
        //Then
        List<MovieDTO> listMovieFinded = movieService.findNeededMovies(movieFinded.getName());
        assertEquals(listMovieCast,listMovieFinded);
    }

    @Test
    void editMovieTest() {
        MovieDTO movieInsert = MovieDTO.builder()
                .categoryDTO(categoryDTO)
                .name("One Piece Film: Red")
                .rating(9.4)
                .description("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.")
                .lengthMinute(115).build();

        Movie movieCast = new Movie();
        Category categoryInsert = new Category();
        categoryInsert.setId(1L);
        categoryInsert.setName("Cartoon");
        movieCast.setCategory(categoryInsert);
        movieCast.setRating(9.4);
        movieCast.setName("One Piece Film: Red");
        movieCast.setDescription("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.");
        movieCast.setLengthMinute(115);

        Movie movie = new Movie();
        movie.setName("One Piece Film: Red");
        movie.setCategory(category);
        movie.setRating(9.4);
        movie.setDescription("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.");
        movie.setLengthMinute(115);
        String actual = "Add Complete";

        // When
        when(movieMapper.castDTOToEntity(movieInsert)).thenReturn(movieCast);
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        //Then
        assertEquals(movie,movieCast);
        String expected = movieService.addMovie(movieInsert);
        verify(movieRepository).save(movieCast);
        assertEquals(actual,expected);
    }

    @Test
    void findAllFMoviesTest() {
        MovieDTO movieFinded = MovieDTO.builder()
                .name("Nobita's Little Star Wars 4")
                .categoryDTO(categoryDTO)
                .rating(9.3)
                .description("One day, Nobita picks a small rocket from which a small-sized humanoid alien Papi comes out.")
                .lengthMinute(108).build();
        List<MovieDTO> listMovieCast = new ArrayList<>();
        listMovieCast.add(movieFinded);
        //When
        when(movieRepository.findAll()).thenReturn(movies);
        when(movieMapper.castListEntityToDTO(movies)).thenReturn(listMovieCast);
        //Then
        List<MovieDTO> listMovieFinded = movieService.findAllFMovies();
        assertEquals(listMovieCast,listMovieFinded);
    }

    @Test
    void findBestMovieTest() {
        MovieDTO movieFinded = MovieDTO.builder()
                .name("Nobita's Little Star Wars 4")
                .categoryDTO(categoryDTO)
                .rating(9.3)
                .description("One day, Nobita picks a small rocket from which a small-sized humanoid alien Papi comes out.")
                .lengthMinute(108).build();
        List<MovieDTO> listMovieCast = new ArrayList<>();
        listMovieCast.add(movieFinded);
        //When
        when(movieRepository.findBestMovie()).thenReturn(movies);
        when(movieMapper.castListEntityToDTO(movies)).thenReturn(listMovieCast);
        //Then
        List<MovieDTO> listMovieFinded = movieService.findBestMovie();
        assertEquals(listMovieCast,listMovieFinded);
    }

    @Test
    void findByCategoryTest() {
        MovieDTO movieFinded = MovieDTO.builder()
                .name("Nobita's Little Star Wars 4")
                .categoryDTO(categoryDTO)
                .rating(9.3)
                .description("One day, Nobita picks a small rocket from which a small-sized humanoid alien Papi comes out.")
                .lengthMinute(108).build();
        List<MovieDTO> listMovieCast = new ArrayList<>();
        listMovieCast.add(movieFinded);
        //When
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
        when(movieRepository.findByCategory(category)).thenReturn(movies);
        when(movieMapper.castListEntityToDTO(movies)).thenReturn(listMovieCast);
        //Then
        List<MovieDTO> listMovieFinded = movieService.findByCategory(category.getName());
        assertEquals(listMovieCast,listMovieFinded);
    }


}
