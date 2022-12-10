package fa.training.service;


import fa.training.dto.SeatDTO;
import fa.training.entity.Seat;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface SeatService {
    //Seat không thêm, không xóa.
    ResponseEntity<List<SeatDTO>> getAll();
    ResponseEntity<SeatDTO> editSeat(SeatDTO seat, String userName);

    ResponseEntity<List<SeatDTO>> editSeatList(List<SeatDTO> seat,String username);


    SeatDTO castEntityToDTO(Seat seat);
    List<SeatDTO> castListEntityToDTO(List<Seat> seat);
    Seat castDTOToEntity(SeatDTO seatDTO);
    List<Seat> castListDTOToEntity(List<SeatDTO> seatDTO);
}
