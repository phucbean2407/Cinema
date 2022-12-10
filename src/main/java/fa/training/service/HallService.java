package fa.training.service;


import fa.training.dto.HallDTO;

import fa.training.entity.Hall;
import org.springframework.http.ResponseEntity;

public interface HallService {
    ResponseEntity<HallDTO> editTheaterHall(HallDTO hallDTO);


    ResponseEntity<HallDTO> findByName(String name);

    HallDTO castEntityToDTO(Hall hall);

    Hall castDTOToEntity(HallDTO hallDTO);

}
