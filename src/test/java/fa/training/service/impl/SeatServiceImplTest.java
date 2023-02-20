package fa.training.service.impl;

import fa.training.dto.SeatDTO;
import fa.training.entity.Seat;
import fa.training.mapper.SeatMapper;
import fa.training.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SeatServiceImplTest {

    @InjectMocks
    private SeatServiceImpl seatService;

    @Mock
    private SeatRepository seatRepository;
    @Mock
    private SeatMapper seatMapper;

    private Seat seat;
    private SeatDTO seatDTO;
    List<Seat> seats;
    List<SeatDTO> seatDTOS;
    @BeforeEach
    void setUp(){
        seat = new Seat();
        seat.setId(1);
        seat.setName("01");

        seatDTO = new SeatDTO();
        seatDTO.setName("01");

        seats = List.of(seat);
        seatDTOS = List.of(seatDTO);
    }
    @Test
    void getAll() {
        //Given
        List<SeatDTO> expected = seatDTOS;
        //When
        when(seatRepository.findAll()).thenReturn(seats);
        when(seatMapper.castListEntityToDTO(seats)).thenReturn(seatDTOS);
        //Then
        List<SeatDTO> actual = seatService.getAll();
        assertEquals(expected,actual);
    }

    @Test
    void editSeat() {
        //Given
        String expected = "Edit Complete";
        //When
        when(seatMapper.castDTOToEntity(seatDTO)).thenReturn(seat);
        when(seatRepository.saveAndFlush(any(Seat.class))).thenReturn(seat);
        //Then
        String actual = seatService.editSeat(seatDTO);
        assertEquals(expected,actual);
    }
}