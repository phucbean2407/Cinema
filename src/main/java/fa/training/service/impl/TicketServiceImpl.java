package fa.training.service.impl;

import fa.training.dto.ChooseOrder;
import fa.training.dto.TicketDTO;
import fa.training.entity.Ticket;
import fa.training.mapper.TicketMapper;
import fa.training.repository.ShowTimeSeatRepository;
import fa.training.repository.TicketRepository;
import fa.training.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ShowTimeSeatRepository showTimeSeatRepository;
    private final TicketMapper ticketMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, ShowTimeSeatRepository showTimeSeatRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.showTimeSeatRepository = showTimeSeatRepository;
        this.ticketMapper = ticketMapper;
    }


    @Override
    public String addTicket(ChooseOrder chooseOrder) {
        Ticket ticket = ticketMapper.castDTOToEntity(chooseOrder.getTicketDTO());
            try {
                ticketRepository.save(ticket);
                for(String a : chooseOrder.getNumberSeat()) {
                    showTimeSeatRepository.setSeatOrder(ticket.getMovieShowTime().getId(),a);
                }
                return "Add Complete";
            } catch (Exception e) {
                return e.getMessage();
            }


    }


    @Override
    public List<TicketDTO> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.castListEntityToDTO(tickets);
    }

    @Override
    public List<TicketDTO> findByEmail(String email) {
        List<Ticket> tickets =  ticketRepository.findTicketByEmail(email);
        return ticketMapper.castListEntityToDTO(tickets);
    }




}
