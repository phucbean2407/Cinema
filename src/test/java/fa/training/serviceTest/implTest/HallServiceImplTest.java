package fa.training.serviceTest.implTest;

import fa.training.dto.HallDTO;
import fa.training.entity.Hall;
import fa.training.mapper.HallMapper;
import fa.training.repository.HallRepository;
import fa.training.service.impl.HallServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HallServiceImplTest {
    @InjectMocks
    private HallServiceImpl hallService;

    @Mock
    private HallRepository hallRepository;
    @Mock
    private HallMapper hallMapper;

    private HallDTO hallDTO;

    private Hall hall;
    @BeforeEach
    void setUp() {
        hallDTO = HallDTO.builder().name("A").build();
        hall = new Hall();
        hall.setName("A");
        hall.setId(1);
    }

    @Test
    void editTheaterHall() {
        //Given
        String actual = "Edit Hall complete";
        //When
        when(hallMapper.castDTOToEntity(hallDTO)).thenReturn(hall);
       // when(hallRepository.save(any(Hall.class))).thenReturn(hall);
        //Then
        String expected = hallService.editTheaterHall(hallDTO);
        assertEquals(actual,expected);
    }

    @Test
    void findByName() {
        //Given
        HallDTO hallActual = hallDTO;
        //When
        when(hallRepository.findByName("A")).thenReturn(Optional.of(hall));
        when(hallMapper.castEntityToDTO(hall)).thenReturn(hallDTO);
        //Then
        HallDTO hallExpected = hallService.findByName("A");
        assertEquals(hallActual,hallExpected);
    }
}