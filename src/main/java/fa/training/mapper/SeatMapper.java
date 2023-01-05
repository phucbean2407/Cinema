package fa.training.mapper;

import fa.training.dto.SeatDTO;
import fa.training.entity.Seat;
import fa.training.repository.SeatRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SeatMapper {

    private final SeatRepository seatRepository;

    public SeatMapper(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public SeatDTO castEntityToDTO(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setName(seat.getName());
        return seatDTO;
    }


    public List<SeatDTO> castListEntityToDTO(List<Seat> seats) {
        List<SeatDTO> seatDTOS = new ArrayList<>();
        for(Seat seat: seats) {
            SeatDTO seatDTO = castEntityToDTO(seat);
            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }


    public Seat castDTOToEntity(SeatDTO seatDTO) {
        return seatRepository.findByName(seatDTO.getName()).orElseThrow();
    }


    public List<Seat> castListDTOToEntity(List<SeatDTO> seatDTOS) {
        List<Seat> seats = new ArrayList<>();
        for(SeatDTO seatDTO: seatDTOS) {
            Seat seat = castDTOToEntity(seatDTO);
            seats.add(seat);
        }
        return seats;
    }
}
