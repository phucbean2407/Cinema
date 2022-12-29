package fa.training.service.impl;


import fa.training.dto.PeopleDTO;
import fa.training.dto.RoleDTO;
import fa.training.dto.UserDTO;
import fa.training.entity.People;
import fa.training.entity.login.User;
import fa.training.repository.PeopleRepository;
import fa.training.repository.UserRepository;
import fa.training.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final UserRepository userRepository;

    public PeopleServiceImpl(PeopleRepository peopleRepository, UserRepository userRepository) {
        this.peopleRepository = peopleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity<PeopleDTO> addPeople(PeopleDTO peopleDTO) {
        People people = this.castDTOToEntity(peopleDTO);
        try {
            peopleRepository.save(people);
            PeopleDTO peopleDto = this.castEntityToDTO(peopleRepository.findByEmail(people.getUser().getEmail()));
            return new ResponseEntity<>(peopleDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Boolean> deletePeople(String peopleEmail) {
        try{
            People p = peopleRepository.findByEmail(peopleEmail);
            peopleRepository.delete(p);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(false,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<PeopleDTO> editPeople(PeopleDTO peopleDTO) {
        People people = this.castDTOToEntity(peopleDTO);
        try {
            peopleRepository.saveAndFlush(people);
            PeopleDTO peopleDto = this.castEntityToDTO(peopleRepository.findByEmail(people.getUser().getEmail()));
            return new ResponseEntity<>(peopleDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<PeopleDTO> findByEmail(String peopleEmail) {
        People people = peopleRepository.findByEmail(peopleEmail);
        PeopleDTO peopleDTO = this.castEntityToDTO(people);
        return new ResponseEntity<>(peopleDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PeopleDTO>> getAdmins() {
        List<People> people = peopleRepository.findByRole("ROLE_ADMIN");
        List<PeopleDTO> peopleDTOS= this.castListEntityToDTO(people);
        return new ResponseEntity<>(peopleDTOS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PeopleDTO>> getAllCustomer() {
        List<People> people =  peopleRepository.findByRole("ROLE_USER");
        List<PeopleDTO> peopleDTOS= this.castListEntityToDTO(people);
        return new ResponseEntity<>(peopleDTOS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PeopleDTO>> getCustomerBirthDay(String birthday) {
        List<People> people = peopleRepository.findByRoleAndBirthday("ROLE_USER",birthday);
        List<PeopleDTO> peopleDTOS= this.castListEntityToDTO(people);
        return new ResponseEntity<>(peopleDTOS,HttpStatus.OK);
    }

    @Override
    public PeopleDTO castEntityToDTO(People people) {
            PeopleDTO peopleDTO = PeopleDTO.builder().build();
            User newUser = userRepository.findByUsername(people.getUser().getUsername()).orElseThrow();
            UserDTO userDTO = UserDTO.builder().build();
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
            return peopleDTO;
    }

    @Override
    public List<PeopleDTO> castListEntityToDTO(List<People> people) {
        List<PeopleDTO> peopleDTOS= new ArrayList<>();
        for (People person : people) {
            PeopleDTO personDTO = this.castEntityToDTO(person);
            peopleDTOS.add(personDTO);
        }
        return peopleDTOS;
    }

    @Override
    public People castDTOToEntity(PeopleDTO peopleDTO) {
        People person = new People();
        if(peopleRepository.findByEmail(peopleDTO.getUserDTO().getEmail()) !=null) {
            person = peopleRepository.findByEmail(peopleDTO.getUserDTO().getEmail());
        }
        person.setName(peopleDTO.getName());
        person.setPhone(peopleDTO.getPhone());
        person.setAddress(peopleDTO.getAddress());
        User user = userRepository.findByUsername(peopleDTO.getUserDTO().getUsername()).orElseThrow();
        person.setUser(user);
        return person;
    }
}
