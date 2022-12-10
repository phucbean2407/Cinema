package fa.training.service;


import fa.training.dto.ChooseOrder;
import fa.training.dto.TicketDTO;
import fa.training.entity.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {

    ResponseEntity<TicketDTO> addTicket(ChooseOrder order);


    ResponseEntity<List<TicketDTO>> findAll();

    ResponseEntity<List<TicketDTO>> findByEmail(String email);


    TicketDTO castEntityToDTO(Ticket ticket);
    List<TicketDTO> castListEntityToDTO(List<Ticket> ticket);
    Ticket castDTOToEntity(TicketDTO ticketDTO);

}
