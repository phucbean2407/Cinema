package fa.training.service.impl;

import fa.training.dto.PeopleDTO;
import fa.training.dto.UserDTO;
import fa.training.entity.People;
import fa.training.entity.User;
import fa.training.mapper.PeopleMapper;
import fa.training.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PeopleServiceImplTest {
    @InjectMocks
    private PeopleServiceImpl peopleService;

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private PeopleMapper peopleMapper;


    private String username, email;
    private PeopleDTO peopleDTO;
    private UserDTO userDTO;

    private People people;
    private User user;
    @BeforeEach
    void setUp() {

        String name = "Do Tien Phuc";
        Date birthday = fa.training.service.utils.DateTimeUtils.fromStringToDate("1998-07-24");
        String address = "9 Le Ngo Cat";
        String phone = "0976752602";
        username = "phucbean2407";
        email = "phucbean2407@gmail.com";
        userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        peopleDTO = new PeopleDTO();
        peopleDTO.setName(name);
        peopleDTO.setPhone(phone);
        peopleDTO.setAddress(address);
        peopleDTO.setBirthday(birthday);
        peopleDTO.setUserDTO(userDTO);

        user = new User(username,email,"123456");
        people = new People();
        people.setName(name);
        people.setPhone(phone);
        people.setAddress(address);
        people.setBirthday(birthday);
        people.setUser(user);
    }

    @Test
    void addPeople() {
        //Given
        String expected = "Add Complete";
        //When
        when(peopleMapper.castDTOToEntity(peopleDTO)).thenReturn(people);
        when(peopleRepository.save(any(People.class))).thenReturn(people);
        //Then
        String actual = peopleService.addPeople(peopleDTO);
        assertEquals(expected,actual);
    }

    @Test
    void editPeople() {
        String expected = "Edit Complete";
        //When
        when(peopleMapper.castDTOToEntity(peopleDTO)).thenReturn(people);
        when(peopleRepository.saveAndFlush(any(People.class))).thenReturn(people);
        //Then
        String actual = peopleService.editPeople(peopleDTO);
        assertEquals(expected,actual);
    }

    @Test
    void findByEmail() {
        PeopleDTO peopleDTOExpected = peopleDTO;
        //When
        when(peopleRepository.findByEmail(email)).thenReturn(Optional.of(people));
        when(peopleMapper.castEntityToDTO(people)).thenReturn(peopleDTO);
        //Then
        PeopleDTO peopleDTOActual = peopleService.findByEmail(email);
        assertEquals(peopleDTOExpected,peopleDTOActual);
    }

    @Test
    void getAdmins() {
        List<People> admins = new ArrayList<>();
        admins.add(people);
        List<PeopleDTO> peopleDTOS = new ArrayList<>();
        peopleDTOS.add(peopleDTO);
        List<PeopleDTO> expected = new ArrayList<>();
        expected.add(peopleDTO);
        //When
        when(peopleRepository.findByRole("ROLE_ADMIN")).thenReturn(admins);
        when(peopleMapper.castListEntityToDTO(admins)).thenReturn(peopleDTOS);
        //Then
        List<PeopleDTO> actual = peopleService.getAdmins();
        assertEquals(expected,actual);
    }

    @Test
    void getAllCustomer() {
        List<People> admins = new ArrayList<>();
        admins.add(people);
        List<PeopleDTO> peopleDTOS = new ArrayList<>();
        peopleDTOS.add(peopleDTO);
        List<PeopleDTO> expected = new ArrayList<>();
        expected.add(peopleDTO);
        //When
        when(peopleRepository.findByRole("ROLE_USER")).thenReturn(admins);
        when(peopleMapper.castListEntityToDTO(admins)).thenReturn(peopleDTOS);
        //Then
        List<PeopleDTO> actual = peopleService.getAllCustomer();
        assertEquals(expected,actual);
    }

    @Test
    void getCustomerBirthDay() {
        String customerBirthDay = "1998-07-24";
        List<People> admins = new ArrayList<>();
        admins.add(people);
        List<PeopleDTO> peopleDTOS = new ArrayList<>();
        peopleDTOS.add(peopleDTO);
        List<PeopleDTO> expected = new ArrayList<>();
        expected.add(peopleDTO);
        //When
        when(peopleRepository.findByRoleAndBirthday("ROLE_USER",customerBirthDay)).thenReturn(admins);
        when(peopleMapper.castListEntityToDTO(admins)).thenReturn(peopleDTOS);
        //Then
        List<PeopleDTO> actual = peopleService.getCustomerBirthDay(customerBirthDay);
        assertEquals(expected,actual);
    }

    @Test
    void deletePeople() {
        //Given
        Boolean expected = true;
        //When
        when(peopleRepository.findByEmail(peopleDTO.getUserDTO().getEmail()))
                .thenReturn(Optional.of(people));
        //Then
        Boolean actual = peopleService.deletePeople(peopleDTO.getUserDTO().getEmail());
        verify(peopleRepository).delete(people);
        assertEquals(expected,actual);
    }
    @Test
    void deletePeopleFalse() {
        //Given
        Boolean expected = false;
        //When
        when(peopleRepository.findByEmail(peopleDTO.getUserDTO().getEmail()))
                .thenReturn(Optional.empty());
        //Then
        Boolean actual = peopleService.deletePeople(peopleDTO.getUserDTO().getEmail());;
        assertEquals(expected,actual);
    }
}