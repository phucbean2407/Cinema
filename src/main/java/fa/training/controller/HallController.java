package fa.training.controller;



import fa.training.dto.HallDTO;

import fa.training.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class HallController {
    @Autowired
    HallService hallService;

    @PostMapping("/edit_hall")
    public ResponseEntity<HallDTO> editHall(@Valid @RequestBody HallDTO hallDTO){
        return hallService.editTheaterHall(hallDTO);
    }

    @GetMapping("/find_hall_by_name")
    public ResponseEntity<HallDTO> getName(@RequestParam("name") String name){
        return hallService.findByName(name);
    }
}


