package fa.training.service;


import fa.training.dto.TheaterHallDTO;

import fa.training.entity.TheaterHall;
import org.springframework.http.ResponseEntity;

public interface TheaterHallService {
    ResponseEntity<TheaterHallDTO> editTheaterHall(TheaterHallDTO hallDTO);

    void setHallFull(String name);
    void setHallReady(String name);

    boolean checkFull(String name);

    ResponseEntity<TheaterHallDTO> findByName(String name);

    TheaterHallDTO castEntityToDTO(TheaterHall theaterHall);

    TheaterHall castDTOToEntity(TheaterHallDTO theaterHallDTO);

}
