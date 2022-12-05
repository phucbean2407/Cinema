package fa.training.controller;


import fa.training.dto.SeatDTO;
import fa.training.dto.TicketDTO;
import fa.training.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/add_ticket")
    public ResponseEntity<TicketDTO> addTicket(@RequestBody @Valid TicketDTO ticketDTO, @RequestBody List<SeatDTO> seatDTOS ) throws NullPointerException{
        return ticketService.addTicket(ticketDTO,seatDTOS);
    }


    @PostMapping("/edit_ticket")
    public ResponseEntity<TicketDTO> editTicket(@RequestBody TicketDTO ticketDTO, @RequestParam("peopleEmail") String email) throws NullPointerException{
        return ticketService.editTicket(ticketDTO, email);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDTO>>  getAll(){
        return ticketService.findAll();
    }

    @GetMapping("/get_ticket_for_customer")
    public ResponseEntity<TicketDTO> findByCustomerEmail(@RequestParam("customerEmail") String email){
        return ticketService.findByCustomerEmail(email);
    }

    @GetMapping("/get_ticket_by_hall")
    public ResponseEntity<List<TicketDTO>> findByHall(@RequestParam("hallName") String name){
        return ticketService.findByHall(name);
    }




}
