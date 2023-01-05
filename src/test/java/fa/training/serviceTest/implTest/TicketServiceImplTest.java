package fa.training.serviceTest.implTest;

import fa.training.dto.ChooseOrder;
import fa.training.dto.TicketDTO;
import fa.training.entity.Ticket;
import fa.training.repository.*;
import fa.training.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TicketServiceImplTest {
    @InjectMocks
    private TicketServiceImpl ticketService;
    @Mock
    private HallRepository hallRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private MovieShowTimeRepository movieShowTimeRepository;
    @Mock
    private ShowTimeSeatRepository showTimeSeatRepository;
    @Mock
    private PeopleRepository peopleRepository;

    private ChooseOrder chooseOrder;
    private TicketDTO ticketDTO;
    private Ticket ticket;
    @BeforeEach
    void setUp() {

    }

    @Test
    void addTicketTest() {
    }

    @Test
    void findAllTest() {
    }

    @Test
    void findByEmailTest() {
    }
}