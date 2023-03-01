package fa.training.service.impl;

import fa.training.dto.*;
import fa.training.entity.*;
import fa.training.entity.User;
import fa.training.enumerates.Time;
import fa.training.mapper.TicketMapper;
import fa.training.repository.PeopleRepository;
import fa.training.repository.ShowTimeSeatRepository;
import fa.training.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketMapper ticketMapper;

    @Mock
    private ShowTimeSeatRepository showTimeSeatRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private EntityManager entityManager;
    private ChooseOrder chooseOrder;
    private TicketDTO ticketDTO;
    private MovieShowTime movieShowTime;
    private MovieShowTimeDTO movieShowTimeDTO;
    private Time time;
    private Hall hall;
    private Movie movie;
    private MovieDTO movieDTO;
    private HallDTO hallDTO;
    private String username, email;
    private PeopleDTO peopleDTO;
    private UserDTO userDTO;

    private People people;
    private User user;
    private long id;
    private String paymentMethod;
    private double price;
    private int quantity;
    private double totalMoney;
    private List<String> numberSeat;
    private Ticket ticket;
    private List<Ticket> tickets;
    private List<TicketDTO> ticketDTOS;
    @BeforeEach
    void setUp() {

        numberSeat = new ArrayList<>();
        numberSeat.add("01");
        numberSeat.add("02");
        time = Time.FIRST;
        Date date = fa.training.service.utils.DateTimeUtils.fromStringToDate("2022-12-15");
        hallDTO = HallDTO.builder().name("A").build();
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Cartoon").build();
        movieDTO = MovieDTO.builder()
                .name("Doraemon")
                .description("AABBCC")
                .lengthMinute(180)
                .categoryDTO(categoryDTO.getName())
                .rating(8.9)
                .build();
        movieShowTimeDTO = MovieShowTimeDTO.builder()
                .time(time)
                .date(date)
                .hallDTO(hallDTO)
                .movieDTO(movieDTO)
                .build();
        username = "phucbean2407";
        email = "phucbean2407@gmail.com";
        userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        String name = "Do Tien Phuc";
        Date birthday = fa.training.service.utils.DateTimeUtils.fromStringToDate("1998-07-24");
        String address = "9 Le Ngo Cat";
        String phone = "0976752602";
        peopleDTO = new PeopleDTO();
        peopleDTO.setName(name);
        peopleDTO.setPhone(phone);
        peopleDTO.setAddress(address);
        peopleDTO.setBirthday(birthday);
        peopleDTO.setUserDTO(userDTO);

        id = 1L;
        paymentMethod = "Cash";
        price = 50000;
        quantity = 1;
        totalMoney = 50000;
        ticketDTO = TicketDTO.builder()
                .id(id)
                .paymentMethod(paymentMethod)
                .price(price)
                .quantity(quantity)
                .totalMoney(totalMoney)
                .peopleDTO(peopleDTO)
                .movieShowTimeDTO(movieShowTimeDTO)
                .build();
        chooseOrder = ChooseOrder.builder()
                .ticketDTO(ticketDTO)
                .numberSeat(numberSeat).build();
        hall = new Hall();
        hall.setId(1L);
        hall.setName("A");
        movie = new Movie();
        Category category = new Category();
        category.setName("Cartoon");
        category.setId(1L);
        movie.setName("Doraemon");
        movie.setId(1);
        movie.setCategory(category);
        movie.setRating(8.9);
        movie.setDescription("AABBCC");
        movie.setLengthMinute(180);
        movieShowTime  = new MovieShowTime();
        movieShowTime.setDate(date);
        movieShowTime.setMovie(movie);
        movieShowTime.setHall(hall);
        movieShowTime.setTime(time);

        user = new User(username,email,"123456");
        people = new People();
        people.setName(name);
        people.setPhone(phone);
        people.setAddress(address);
        people.setBirthday(birthday);
        people.setUser(user);

        ticket = new Ticket();
        ticket.setId(id);
        ticket.setPaymentMethod(paymentMethod);
        ticket.setPrice(price);
        ticket.setQuantity(quantity);
        ticket.setTotalMoney(totalMoney);
        ticket.setPeople(people);
        ticket.setMovieShowTime(movieShowTime);

        tickets = new ArrayList<>();
        tickets.add(ticket);
        ticketDTOS = new ArrayList<>();
        ticketDTOS.add(ticketDTO);
    }

    @Test
    void addTicketTest() {
        //Given
        String actual = "Add Complete";
        //ShowTimeSeatRepository showTimeSeat = mock(ShowTimeSeatRepository.class);
        //When
        when(ticketMapper.castDTOToEntity(chooseOrder.getTicketDTO())).thenReturn(ticket);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        //doNothing().when(showTimeSeat).setSeatOrder(1,"01");
        //Then
        String expected = ticketService.addTicket(chooseOrder);
        assertEquals(actual,expected);
    }

    @Test
    void findAllTest() {
        //Given
        List<TicketDTO> ticketActualList= new ArrayList<>();
        TicketDTO ticketActual = ticketDTO;
        ticketActualList.add(ticketActual);
        //When
        when(ticketRepository.findAll()).thenReturn(tickets);
        when(ticketMapper.castListEntityToDTO(tickets)).thenReturn(ticketDTOS);
       // when(peopleRepository.findByEmail(email)).thenReturn(Optional.of(people));

        //Then
        List<TicketDTO> ticketExpectedList = ticketService.findAll();
        assertEquals(ticketActualList,ticketExpectedList);
    }

    @Test
    void findByEmailTest() {
        List<TicketDTO> ticketActualList= new ArrayList<>();
        TicketDTO ticketActual = ticketDTO;
        ticketActualList.add(ticketActual);
        //When
        when(ticketRepository.findTicketByEmail(email)).thenReturn(tickets);
        when(ticketMapper.castListEntityToDTO(tickets)).thenReturn(ticketDTOS);


        //Then
        List<TicketDTO> ticketExpectedList = ticketService.findByEmail(email);
        assertEquals(ticketActualList,ticketExpectedList);
    }
}