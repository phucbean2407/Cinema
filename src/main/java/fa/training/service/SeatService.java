package fa.training.service;


import fa.training.dto.SeatDTO;

import java.util.List;

public interface SeatService {
    //Seat không thêm, không xóa.
    List<SeatDTO> getAll();
    String editSeat(SeatDTO seat, String userName);


}
