package fa.training.service.impl;

import fa.training.dto.*;
import fa.training.entity.*;
import fa.training.entity.login.User;
import fa.training.repository.*;
import fa.training.service.TicketService;
import fa.training.service.utils.DateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;
    private final MovieShowTimeRepository movieShowTimeRepository;
    private final ShowTimeSeatRepository showTimeSeatRepository;
    private final PeopleRepository peopleRepository;
    private final UserRepository userRepository;

    public TicketServiceImpl(HallRepository hallRepository, MovieRepository movieRepository, TicketRepository ticketRepository, MovieShowTimeRepository movieShowTimeRepository, SeatRepository seatRepository, PeopleRepository peopleRepository, UserRepository userRepository, ShowTimeSeatRepository showTimeSeatRepository) {
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.movieShowTimeRepository = movieShowTimeRepository;
        this.peopleRepository = peopleRepository;
        this.userRepository = userRepository;
        this.showTimeSeatRepository = showTimeSeatRepository;
    }

    @Override
    public ResponseEntity<TicketDTO> addTicket(ChooseOrder chooseOrder) {
        Ticket ticket = this.castDTOToEntity(chooseOrder.getTicketDTO());

            try {
                ticketRepository.save(ticket);
                for(String a : chooseOrder.getNumberSeat()) {
                    showTimeSeatRepository.setSeatOrder(ticket.getMovieShowTime().getId(),a);
                }
                TicketDTO ticketDTO1 = this.castEntityToDTO(ticket);
                return new ResponseEntity<>(ticketDTO1, HttpStatus.CREATED);
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
    public ResponseEntity<List<TicketDTO>> findByEmail(String email) {
        List<Ticket> tickets =  ticketRepository.findTicketByEmail(email);
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
        //Set PeopleDTO
        People people = peopleRepository.findByEmail(ticket.getPeople().getUser().getEmail());
        PeopleDTO peopleDTO = new PeopleDTO();
        User newUser =  people.getUser();
        UserDTO userDTO = new UserDTO();
        Set<RoleDTO> roleDTOS = newUser.getRoles().stream()
                .map(role -> {
                    RoleDTO roleDto = new RoleDTO();
                    roleDto.setName(role.getName());
                    return roleDto;
                }).collect(Collectors.toSet());
        userDTO.setRoleDTOs(roleDTOS);
        userDTO.setUsername(newUser.getUsername());
        userDTO.setEmail(newUser.getEmail());
        peopleDTO.setUserDTO(userDTO);
        peopleDTO.setName(people.getName());
        peopleDTO.setPhone(people.getPhone());
        peopleDTO.setAddress(people.getAddress());
        peopleDTO.setBirthday(people.getBirthday());
        ticketDTO.setPeopleDTO(peopleDTO);
        //Set MovieShowTime
        MovieShowTime movieShowTime = ticket.getMovieShowTime();
        MovieShowTimeDTO movieShowTimeDTO = new MovieShowTimeDTO();
        movieShowTimeDTO.setDate(movieShowTime.getDate());
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

        Hall hall = movieShowTime.getHall();
        HallDTO hallDTO = new HallDTO();
        hallDTO.setName(hall.getName());
        movieShowTimeDTO.setHallDTO(hallDTO);

        movieShowTimeDTO.setTime(movieShowTime.getTime());
        Set<SeatDTO>  seatDTOS = movieShowTime.getSeats().stream()
                .map(seat -> {
                    SeatDTO seatDTO = new SeatDTO();
                    seatDTO.setName(seat.getName());
                    return seatDTO;
                }).collect(Collectors.toSet());
        movieShowTimeDTO.setSeatDTOS(seatDTOS);

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
        String date = DateTimeUtils.fromDateToString(ticketDTO.getMovieShowTimeDTO().getDate());
        String movieName = ticketDTO.getMovieShowTimeDTO().getMovieDTO().getName();
        String hallName =  ticketDTO.getMovieShowTimeDTO().getHallDTO().getName();
        long timeId = ticketDTO.getMovieShowTimeDTO().getTime().getId();
        Time time = ticketDTO.getMovieShowTimeDTO().getTime();
        MovieShowTime movieShowTime = movieShowTimeRepository
                .findForTicket(date,
                        movieName,
                        hallName,
                        timeId).orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
        movieShowTime.setTime(time);
        movieShowTime.setDate(ticketDTO.getMovieShowTimeDTO().getDate());
        Movie movie = movieRepository.findByName(movieName);
        movieShowTime.setMovie(movie);
        Hall hall = hallRepository.findByName(hallName);
        movieShowTime.setHall(hall);
        ticket.setMovieShowTime(movieShowTime);
        ticket.setPaymentMethod(ticketDTO.getPaymentMethod());
        ticket.setPrice(ticketDTO.getPrice());
        ticket.setQuantity(ticketDTO.getQuantity());
        ticket.setTotalMoney(ticketDTO.getPrice()*ticketDTO.getQuantity());
        PeopleDTO peopleDTO = ticketDTO.getPeopleDTO();
        People people = peopleRepository.findByEmail(peopleDTO.getUserDTO().getEmail());
        people.setBirthday(peopleDTO.getBirthday());
        people.setName(peopleDTO.getName());
        people.setAddress(peopleDTO.getAddress());
        people.setName(peopleDTO.getName());
        people.setTicketSet(null);
        people.setPhone(peopleDTO.getPhone());
        ticket.setPeople(people);
        return ticket;
    }
}
