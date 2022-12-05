package fa.training.controller;



import fa.training.dto.TheaterHallDTO;

import fa.training.service.TheaterHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class TheaterHallController {
    @Autowired
    TheaterHallService hallService;

    @PostMapping("/edit_hall")
    public ResponseEntity<TheaterHallDTO> editHall(@Valid @RequestBody TheaterHallDTO hallDTO){
        return hallService.editTheaterHall(hallDTO);
    }

    @GetMapping("/find_hall_by_name")
    public ResponseEntity<TheaterHallDTO> getName(@RequestParam("name") String name){
        return hallService.findByName(name);
    }
}


