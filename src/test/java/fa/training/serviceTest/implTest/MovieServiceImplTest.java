package fa.training.serviceTest.implTest;

import fa.training.dto.CategoryDTO;
import fa.training.dto.MovieDTO;
import fa.training.entity.Category;
import fa.training.entity.Movie;
import fa.training.repository.CategoryRepository;
import fa.training.repository.MovieRepository;
import fa.training.service.impl.MovieServiceImpl;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Builder
@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CategoryRepository categoryRepository;
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

        categoryDTO = new CategoryDTO();
        categoryDTO.setName("Cartoon");

        category = new Category();
        category.setId(1);
        category.setName("Cartoon");

        movieDTO = new MovieDTO();
        movieDTO.setName("Doraemon");
        movieDTO.setDescription("AAABBBCCC");
        movieDTO.setRating(8.9);
        movieDTO.setLengthMinute(180);
        movieDTO.setCategoryDTO(categoryDTO);

        movies = new ArrayList<>();

        movieDTOS = new ArrayList<>();


        movie = new Movie();
        movie.setName("Nobita's Little Star Wars 4");
        movie.setId(6L);
        movie.setCategory(category);
        movie.setRating(9.3);
        movie.setDescription("One day, Nobita picks a small rocket from which a small-sized humanoid alien Papi comes out.");
        movie.setLengthMinute(108);
    }

    @Test
    void findNeededMoviesTest(){
        //Given
        //Exists on setUp()

        //When
        when(movieRepository.findNeededMovies(movieDTO.getName())).thenReturn(movies);
        //Then
        movieDTOS = movieService.castListEntityToDTO(movies);
        List<MovieDTO> movieDTOS2 = movieService.findNeededMovies(movieDTO.getName()).getBody();
        assertEquals(movieDTOS2,movieDTOS);
    }
    @Test
    void addMovieTest(){
        //Given
        MovieDTO movieInsert = new MovieDTO();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("One Piece Film: Red");
        movieInsert.setCategoryDTO(categoryDTO);
        movieInsert.setRating(9.4);
        movieInsert.setDescription("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.");
        movieInsert.setLengthMinute(115);
        //Exists on setUp()
        when(movieRepository.save(any(Movie.class))).then(new Answer<Movie>() {
            int sequence = 3;
            @Override
            public Movie answer(InvocationOnMock invocation) {
                Movie movie = invocation.getArgument(0);
                movie.setName("One Piece Film: Red");
                movie.setId(sequence++);
//                Category categoryAdded = Category
//                        .builder()
//                        .id(1L)
//                        .name("Cartoon").build();
                movie.setCategory(category);
                movie.setRating(9.4);
                movie.setDescription("The story is set on the Island of Music Elegia, where Uta, the world's greatest diva, holds her first ever live concert and reveals herself to the public. The Straw Hats, pirates, Marines and fans from across the world gather to enjoy Uta's voice, which has been described as otherworldly. However, the event begins with the shocking revelation that Uta is the daughter of Shanks.");
                movie.setLengthMinute(115);
                return movie;
            }
        });
        //Then
        MovieDTO insertedDTO = movieService.addMovie(movieInsert).getBody();
        verify(movieRepository).save(movie);
        assertNotNull(movie);
        assertEquals(movieInsert,insertedDTO);
    }
}
