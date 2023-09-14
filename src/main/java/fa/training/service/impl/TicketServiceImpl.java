package fa.training.service.impl;

import fa.training.dto.ChooseOrder;
import fa.training.dto.TicketDTO;
import fa.training.entity.Seat;
import fa.training.entity.Ticket;
import fa.training.exception.SeatExistsException;
import fa.training.mapper.TicketMapper;
import fa.training.repository.RoleRepository;
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
    private final RoleRepository roleRepository;
    public TicketServiceImpl(TicketRepository ticketRepository, ShowTimeSeatRepository showTimeSeatRepository, TicketMapper ticketMapper,
                             RoleRepository roleRepository) {
        this.ticketRepository = ticketRepository;
        this.showTimeSeatRepository = showTimeSeatRepository;
        this.ticketMapper = ticketMapper;
        this.roleRepository = roleRepository;
    }
    @Override
    public String addTicket(ChooseOrder chooseOrder) throws SeatExistsException {
        Ticket ticket = ticketMapper.castDTOToEntity(chooseOrder.getTicketDTO());
        ticketRepository.save(ticket);
        for(String numberSeat : chooseOrder.getNumberSeat()) {
            for(Seat seatOrdered : ticket.getMovieShowTime().getSeats()){
                if(seatOrdered.getName().equals(numberSeat)) {
                    throw new SeatExistsException("Seat: " + numberSeat +" is ordered");
                }
            }
            long movieShowTimeId = ticket.getMovieShowTime().getId();
            showTimeSeatRepository.setSeatOrder(movieShowTimeId, numberSeat);
            }
        //Repository existById
        return "Add Complete";
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
