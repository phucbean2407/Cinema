package fa.training.service;



import fa.training.dto.SeatDTO;
import fa.training.dto.TicketDTO;
import fa.training.entity.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {

    ResponseEntity<TicketDTO> addTicket(TicketDTO ticketDTO, List<SeatDTO> seatDTO);


    //Pass vé xem phim :))))
    ResponseEntity<TicketDTO> editTicket(TicketDTO ticketDTO, String peopleEmail);

    ResponseEntity<List<TicketDTO>> findAll();

    ResponseEntity<TicketDTO> findByCustomerEmail(String email);

    ResponseEntity<List<TicketDTO>> findByHall(String hallName);

    TicketDTO castEntityToDTO(Ticket ticket);
    List<TicketDTO> castListEntityToDTO(List<Ticket> ticket);
    Ticket castDTOToEntity(TicketDTO ticketDTO);

}
