package fa.training.service.impl;

import fa.training.dto.TheaterHallDTO;
import fa.training.entity.Seat;
import fa.training.entity.TheaterHall;
import fa.training.repository.SeatRepository;
import fa.training.repository.TheaterHallRepository;
import fa.training.service.SeatService;
import fa.training.service.TheaterHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterHallServiceImpl implements TheaterHallService{
    @Autowired
    TheaterHallRepository hallRepository;
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    SeatService seatService;

    @Override
    public ResponseEntity<TheaterHallDTO> editTheaterHall(TheaterHallDTO hallDTO) {
        try{
            TheaterHall theaterHall = this.castDTOToEntity(hallDTO);
            int count = 0;
            for(Seat seat : seatRepository.findSeatFromHall(theaterHall.getName())){
                if(!seat.isActive()) {
                    count++;
                }
            }
            if(count == seatRepository.findSeatFromHall(theaterHall.getName()).size() || hallDTO.isFull()) {
                this.setHallFull(theaterHall.getName());
            } else {
                this.setHallReady(theaterHall.getName());
            }
            TheaterHallDTO theaterHal = this.castEntityToDTO(hallRepository.findByName(theaterHall.getName()));
            theaterHal.setSeatDTO(seatService.findSeatFromHall(theaterHal.getName()).getBody());
            return new ResponseEntity<>(theaterHal, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(),HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<TheaterHallDTO> findByName(String name) {
        TheaterHall theaterHall = hallRepository.findByName(name);
        TheaterHallDTO hallDTO = this.castEntityToDTO(theaterHall);
        return new ResponseEntity<>(hallDTO, HttpStatus.OK);
    }
    @Override
    public void setHallFull(String name) {
        TheaterHall theaterHall = hallRepository.findByName(name);
        theaterHall.setFull(true);
        List<Seat> seatList = seatRepository.findSeatFromHall(name);
        try {
            for(Seat a : seatList){
                a.setActive(false);
                seatRepository.saveAndFlush(a);
            }
            hallRepository.saveAndFlush(theaterHall);
        } catch (Exception e) {

        }
    }
    @Override
    public void setHallReady(String name) {
        TheaterHall theaterHall = hallRepository.findByName(name);
        theaterHall.setFull(false);
        List<Seat> seatList = seatRepository.findSeatFromHall(name);
        try{
            for(Seat a : seatList){
                a.setActive(true);
                seatRepository.saveAndFlush(a);
            }
            hallRepository.saveAndFlush(theaterHall);
        } catch (Exception e) {

        }

    }
    @Override
    public boolean checkFull(String name) {
        TheaterHall theaterHall = hallRepository.findByName(name);
        int count = 0;
        for(Seat seat : theaterHall.getSeat()){
            if(!seat.isActive()) {
                count++;
            }
        }
        if(count == theaterHall.getSeat().size()){
            theaterHall.setFull(true);
            hallRepository.saveAndFlush(theaterHall);
            return true;
        }
        else{
                theaterHall.setFull(false);
                hallRepository.saveAndFlush(theaterHall);
                return false;
        }
    }
    @Override
    public TheaterHallDTO castEntityToDTO(TheaterHall theaterHall) {
        TheaterHallDTO theaterHallDTO = new TheaterHallDTO();
        theaterHallDTO.setFull(theaterHall.isFull());
        theaterHallDTO.setName(theaterHall.getName());
        theaterHallDTO.setSeatDTO(seatService.castListEntityToDTO(seatRepository.findSeatFromHall(theaterHall.getName())));
        return theaterHallDTO;
    }
    @Override
    public TheaterHall castDTOToEntity(TheaterHallDTO theaterHallDTO) {
        TheaterHall theaterHall = new TheaterHall();
        if(hallRepository.findByName(theaterHall.getName()) !=null){
            theaterHall = hallRepository.findByName(theaterHall.getName());
        }
        theaterHall.setName(theaterHallDTO.getName());
        theaterHall.setFull(theaterHallDTO.isFull());
        return theaterHall;
    }
}
