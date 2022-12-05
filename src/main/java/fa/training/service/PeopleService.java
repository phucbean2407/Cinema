package fa.training.service;


import fa.training.dto.PeopleDTO;
import fa.training.entity.People;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PeopleService {
    ResponseEntity<PeopleDTO> addPeople(PeopleDTO peopleDTO);
    ResponseEntity<Boolean> deletePeople(String peopleEmail);
    ResponseEntity<PeopleDTO> editPeople(PeopleDTO peopleDTO);
    ResponseEntity<PeopleDTO> findByEmail(String peopleEmail);
    ResponseEntity<List<PeopleDTO>> getAllStaff();
    ResponseEntity<List<PeopleDTO>> getAllCustomer();
    ResponseEntity<List<PeopleDTO>> getCustomerBirthDay(String birthDay);

    PeopleDTO castEntityToDTO(People people);
    List<PeopleDTO> castListEntityToDTO(List<People> people);
    People castDTOToEntity(PeopleDTO peopleDTO);
}
