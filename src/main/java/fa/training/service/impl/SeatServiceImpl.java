package fa.training.service.impl;


import fa.training.dto.SeatDTO;
import fa.training.config.AppConfig;
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
    SeatRepository seatRepository;
    @Autowired
    AppConfig appConfig;

    @Override
    public ResponseEntity<List<SeatDTO>> getAll() {
        List<Seat> seats =  seatRepository.findAll();
        List<SeatDTO> seatDTOS= this.castListEntityToDTO(seats);
        return new ResponseEntity<>(seatDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeatDTO> editSeat(SeatDTO seatDTO) {
        Seat seat = this.castDTOToEntity(seatDTO);
        try{
            seatRepository.saveAndFlush(seat);
            SeatDTO seatDto = this.castEntityToDTO(seatRepository.findByName(seat.getName()));
            return new ResponseEntity<>(seatDto,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<SeatDTO>> editSeatList(List<SeatDTO> seatDTOS) {
        try{
            List<Seat> seats = this.castListDTOToEntity(seatDTOS);
            for(Seat seat : seats) {
                seatRepository.saveAndFlush(seat);
            }
            return new ResponseEntity<>(seatDTOS,HttpStatus.OK);
        } catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<SeatDTO>> findSeatFromHall(String name) {
        List<Seat> seats = seatRepository.findSeatFromHall(name);
        List<SeatDTO> seatDTOS = this.castListEntityToDTO(seats);
        return new ResponseEntity<>(seatDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SeatDTO>> findFreeSeatFromHall(String name) {
        List<Seat> seats = seatRepository.findFreeSeatFromHall(name);
        List<SeatDTO> seatDTOS = this.castListEntityToDTO(seats);
        return new ResponseEntity<>(seatDTOS, HttpStatus.OK);
    }

    @Override
    public SeatDTO castEntityToDTO(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setName(seat.getName());
        seat.setActive(seat.isActive());
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
        seat.setActive(seat.isActive());
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
