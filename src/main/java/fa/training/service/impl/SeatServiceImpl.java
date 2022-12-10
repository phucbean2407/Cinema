package fa.training.service.impl;


import fa.training.dto.SeatDTO;
import fa.training.entity.Seat;
import fa.training.repository.SeatRepository;
import fa.training.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public ResponseEntity<List<SeatDTO>> getAll() {
        List<Seat> seats =  seatRepository.findAll();
        List<SeatDTO> seatDTOS= this.castListEntityToDTO(seats);
        return new ResponseEntity<>(seatDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeatDTO> editSeat(SeatDTO seatDTO, String username) {
        Seat seat = this.castDTOToEntity(seatDTO);
        try{
            SeatDTO seatDto = this.castEntityToDTO(seatRepository.findByName(seat.getName()));
            return new ResponseEntity<>(seatDto,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<SeatDTO>> editSeatList(List<SeatDTO> seatDTOS,String username ) {
        try{
            List<SeatDTO> seatDTOSs = new ArrayList<>();
            List<Seat> seats = this.castListDTOToEntity(seatDTOS);
            for(Seat seat : seats) {
                SeatDTO seatDto = this.castEntityToDTO(seatRepository.findByName(seat.getName()));
                seatDTOSs.add(seatDto);
            }
            return new ResponseEntity<>(seatDTOSs,HttpStatus.OK);
        } catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }


    @Override
    public SeatDTO castEntityToDTO(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setName(seat.getName());
        return seatDTO;
    }

    @Override
    public List<SeatDTO> castListEntityToDTO(List<Seat> seats) {
        List<SeatDTO> seatDTOS = new ArrayList<>();
        for(Seat seat: seats) {
            SeatDTO seatDTO = this.castEntityToDTO(seat);
            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }

    @Override
    public Seat castDTOToEntity(SeatDTO seatDTO) {
        Seat seat = seatRepository.findByName(seatDTO.getName());
        return seat;
    }

    @Override
    public List<Seat> castListDTOToEntity(List<SeatDTO> seatDTOS) {
        List<Seat> seats = new ArrayList<>();
        for(SeatDTO seatDTO: seatDTOS) {
            Seat seat = this.castDTOToEntity(seatDTO);
            seats.add(seat);
        }
        return seats;
    }
}
