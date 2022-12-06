package fa.training.service.impl;

import fa.training.config.AppConfig;
import fa.training.dto.*;
import fa.training.entity.*;
import fa.training.entity.login.User;
import fa.training.repository.MovieShowTimeRepository;
import fa.training.repository.PeopleRepository;
import fa.training.repository.TicketRepository;
import fa.training.service.SeatService;
import fa.training.service.TheaterHallService;
import fa.training.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    AppConfig appConfig;
    @Autowired
    SeatService seatService;
    @Autowired
    TheaterHallService theaterHallService;

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    MovieShowTimeRepository movieShowTimeRepository;
    @Autowired
    PeopleRepository peopleRepository;
    //Xử lý giờ. Nếu giờ hiện tại < giờ chiếu thì phòng ready để đặt.
    // Nếu giờ hiện tại == set phòng là full không cho đặt, đồng thời call method xóa hết ghế đã đặt tại thời điểm đó

    @Override
    public ResponseEntity<TicketDTO> addTicket(TicketDTO ticketDTO, List<SeatDTO> seatDTOS) {
        Ticket ticket = this.castDTOToEntity(ticketDTO);
        ticket.setQuantity(seatDTOS.size());
        if(!theaterHallService.checkFull(ticket.getMovieShowTime().getTheaterHall().getName())){
            try {
                seatService.editSeatList(seatDTOS);
                ticketRepository.save(ticket);
                TicketDTO ticketDTO1 = this.castEntityToDTO(ticket);
                return new ResponseEntity<>(ticketDTO1, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(),HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity("Hall is full, Please choose another time",HttpStatus.OK);
        }

    }


    //Pass vé xem phim :))))
    @Override
    public ResponseEntity<TicketDTO> editTicket(TicketDTO ticketDTO, String peopleEmail) {
        Ticket ticket = this.castDTOToEntity(ticketDTO);
        ticket.setPeople(peopleRepository.findByEmail(peopleEmail));
        try {
            ticketRepository.saveAndFlush(ticket);
            TicketDTO ticketDTO1 = this.castEntityToDTO(ticketRepository.findById(ticket.getId()).get());
            return new ResponseEntity<>(ticketDTO1, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<TicketDTO>> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> ticketDTOS= this.castListEntityToDTO(tickets);
        return new ResponseEntity<>(ticketDTOS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketDTO> findByCustomerEmail(String email) {
        Ticket ticket =  ticketRepository.findTicketByEmailCustomer(email);
        TicketDTO ticketDTO = this.castEntityToDTO(ticket);
        return new ResponseEntity<>(ticketDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TicketDTO>> findByHall(String hallName) {
        List<Ticket> tickets = ticketRepository.findTicketByHallName(hallName);
        List<TicketDTO> ticketDTOS = this.castListEntityToDTO(tickets);
        return new ResponseEntity<>(ticketDTOS,HttpStatus.OK);

    }

    @Override
    public TicketDTO castEntityToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setPaymentMethod(ticket.getPaymentMethod());
        ticketDTO.setPrice(ticket.getPrice());
        ticketDTO.setQuantity(ticket.getQuantity());
        ticketDTO.setTotalMoney(ticket.getTotalMoney());
        People people = peopleRepository.findByEmail(ticket.getPeople().getUser().getEmail());
        PeopleDTO peopleDTO = new PeopleDTO();
        peopleDTO.setName(people.getName());
        peopleDTO.setPhone(people.getPhone());
        peopleDTO.setAddress(people.getAddress());
        peopleDTO.setBirthday(people.getBirthday());
        User user = people.getUser();
        UserDTO  userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        Set<Role> roles = user.getRoles();
        Set<RoleDTO> roleDTO = roles.stream()
                .map(role -> {
                    RoleDTO roleDto = new RoleDTO();
                    roleDto.setName(role.getName());
                    return roleDto;
                }).collect(Collectors.toSet());
        userDTO.setRoleDTOs(roleDTO);
        peopleDTO.setUserDTO(userDTO);
        ticketDTO.setPeople(peopleDTO);
        MovieShowTime movieShowTime = ticket.getMovieShowTime();
        MovieShowTimeDTO movieShowTimeDTO = new MovieShowTimeDTO();
        movieShowTimeDTO.setDate(movieShowTime.getDate());
        movieShowTimeDTO.setTime(movieShowTime.getTime());
        Movie movie = movieShowTime.getMovie();
        MovieDTO movieDTO = new MovieDTO();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(movie.getCategory().getName());
        movieDTO.setName(movie.getName());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setRating(movie.getRating());
        movieDTO.setLengthMinute(movie.getLengthMinute());
        movieDTO.setCategoryDTO(categoryDTO);
        movieShowTimeDTO.setMovieDTO(movieDTO);
        TheaterHall theaterHall = movieShowTime.getTheaterHall();
        TheaterHallDTO theaterHallDTO = new TheaterHallDTO();
        theaterHallDTO.setName(theaterHall.getName());
        movieShowTimeDTO.setTheaterHallDTO(theaterHallDTO);
        ticketDTO.setMovieShowTimeDTO(movieShowTimeDTO);
        return ticketDTO;
    }

    @Override
    public List<TicketDTO> castListEntityToDTO(List<Ticket> tickets) {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for(Ticket ticket : tickets) {
            TicketDTO ticketDTO = this.castEntityToDTO(ticket);
            ticketDTOS.add(ticketDTO);
        }
        return ticketDTOS;
    }

    @Override
    public Ticket castDTOToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        if(ticketRepository.findById(ticketDTO.getId()).get() !=null) {
            ticket = ticketRepository.findById(ticketDTO.getId()).get();
        }
        MovieShowTime movieShowTime = movieShowTimeRepository.findForTicket(ticketDTO.getMovieShowTimeDTO().getDate(),
                ticketDTO.getMovieShowTimeDTO().getMovieDTO().getName(),
                ticketDTO.getMovieShowTimeDTO().getTheaterHallDTO().getName(),
                ticketDTO.getMovieShowTimeDTO().getTime().getTime());
        ticket.setQuantity(ticketDTO.getQuantity());
        ticket.setTotalMoney(ticketDTO.getPrice()*ticketDTO.getQuantity());
        ticket.setMovieShowTime(movieShowTime);
        ticket.setPeople(peopleRepository.findByEmail(ticketDTO.getPeople().getUserDTO().getEmail()));
        return ticket;
    }
}
