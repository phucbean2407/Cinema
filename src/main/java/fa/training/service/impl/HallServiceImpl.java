package fa.training.service.impl;

import fa.training.dto.HallDTO;
import fa.training.entity.Hall;
import fa.training.repository.HallRepository;
import fa.training.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hallRepository;
    @Override
    public ResponseEntity<HallDTO> editTheaterHall(HallDTO hallDTO) {
        try{
            Hall hall = this.castDTOToEntity(hallDTO);
            HallDTO hallDTO1 = this.castEntityToDTO(hall);
            hallRepository.saveAndFlush(hall);
            return new ResponseEntity<>(hallDTO1, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<HallDTO> findByName(String name) {
        try{
            Hall hall = hallRepository.findByName(name);
            HallDTO hallDTO = this.castEntityToDTO(hall);
            return new ResponseEntity<>(hallDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Wrong name or this hall not exists.",HttpStatus.OK);
        }

    }

    @Override
    public HallDTO castEntityToDTO(Hall hall) {
        HallDTO hallDTO = new HallDTO();
        hallDTO.setName(hall.getName());
        return hallDTO;
    }
    @Override
    public Hall castDTOToEntity(HallDTO hallDTO) {
        Hall hall = new Hall();
        if(hallRepository.findByName(hall.getName()) !=null){
            hall = hallRepository.findByName(hall.getName());
        }
        hall.setName(hallDTO.getName());
        return hall;
    }
}
