package fa.training.controller;


import fa.training.dto.ChooseOrder;
import fa.training.dto.TicketDTO;
import fa.training.exception.SeatExistsException;
import fa.training.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/add_ticket")
    public ResponseEntity<?> addTicket(@RequestBody @Valid ChooseOrder chooseOrder) throws SeatExistsException {
        return ResponseEntity.ok(ticketService.addTicket(chooseOrder));
    }

    @GetMapping("")
    public ResponseEntity<List<TicketDTO>>  getAll(){
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/get-ticket-by-email")
    public ResponseEntity<List<TicketDTO>> findByEmail(@RequestParam("customerEmail") String email){
        return ResponseEntity.ok(ticketService.findByEmail(email));
    }




}
