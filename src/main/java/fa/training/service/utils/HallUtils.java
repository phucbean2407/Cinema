package fa.training.service.utils;

import fa.training.dto.HallDTO;
import fa.training.entity.Hall;
import fa.training.repository.HallRepository;
import org.springframework.stereotype.Component;

@Component
public class HallUtils {

    static HallRepository hallRepository;
    public static HallDTO castEntityToDTO(Hall hall) {
        return HallDTO.builder().name(hall.getName()).build();
    }

    public static Hall castDTOToEntity(HallDTO hallDTO) {
        Hall hall = new Hall();
        if(hallRepository.findByName(hall.getName()).isPresent()){
            hall = hallRepository.findByName(hall.getName()).orElseThrow();
        }
        hall.setName(hallDTO.getName());
        return hall;
    }
}
