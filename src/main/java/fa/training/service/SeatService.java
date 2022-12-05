package fa.training.service;


import fa.training.dto.SeatDTO;
import fa.training.entity.Seat;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface SeatService {
    //Seat không thêm, không xóa.
    ResponseEntity<List<SeatDTO>> getAll();
    ResponseEntity<SeatDTO> editSeat(SeatDTO seat);

    ResponseEntity<List<SeatDTO>> editSeatList(List<SeatDTO> seat);
    ResponseEntity<List<SeatDTO>> findSeatFromHall(String name);

    ResponseEntity<List<SeatDTO>> findFreeSeatFromHall(String name);


    SeatDTO castEntityToDTO(Seat seat);
    List<SeatDTO> castListEntityToDTO(List<Seat> seat);
    Seat castDTOToEntity(SeatDTO seatDTO);
    List<Seat> castListDTOToEntity(List<SeatDTO> seatDTO);
}
