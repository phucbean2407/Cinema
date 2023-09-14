package fa.training.service;


import fa.training.dto.ChooseOrder;
import fa.training.dto.TicketDTO;
import fa.training.exception.SeatExistsException;

import java.util.List;

public interface TicketService {

    String addTicket(ChooseOrder order) throws SeatExistsException;


    List<TicketDTO> findAll();

    List<TicketDTO> findByEmail(String email);


}
