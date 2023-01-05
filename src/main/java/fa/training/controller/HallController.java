package fa.training.controller;


import fa.training.dto.HallDTO;
import fa.training.service.HallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class HallController {
    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping("/edit_hall")
    public ResponseEntity<?> editHall(@Valid @RequestBody HallDTO hallDTO){
        return ResponseEntity.ok(hallService.editTheaterHall(hallDTO));
    }

    @GetMapping("/find_hall_by_name")
    public ResponseEntity<HallDTO> getName(@RequestParam("name") String name){
        return ResponseEntity.ok(hallService.findByName(name));
    }
}


