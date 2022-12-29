package fa.training.controller;


import fa.training.dto.PeopleDTO;
import fa.training.service.PeopleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @PostMapping("/add_person")
    public ResponseEntity<PeopleDTO> addPeople(@RequestBody @Valid PeopleDTO peopleDTO) throws NullPointerException{
        return peopleService.addPeople(peopleDTO);
    }
    @DeleteMapping("/del_person")
    public String deleteCustomer(@RequestParam(name = "email") String email) {
        if(Boolean.TRUE.equals(peopleService.deletePeople(email).getBody())){
            return "Complete";
        }
        else{
            return "Can not delete that person";
        }
    }
    @PostMapping("/edit_person")
    public ResponseEntity<PeopleDTO> editCustomer(@Valid @RequestBody PeopleDTO peopleDTO) throws NullPointerException{
        return peopleService.editPeople(peopleDTO);
    }
    @GetMapping("/get_person")
    public ResponseEntity<PeopleDTO> getPerson(@RequestParam(name = "email") String email){
        return peopleService.findByEmail(email);
    }
    @GetMapping("/get_admin")
    public ResponseEntity<List<PeopleDTO>> getAdmin(){
        return peopleService.getAdmins();
    }

    @GetMapping("/get_birthday")
    public ResponseEntity<List<PeopleDTO>> getPersonBirthday(@RequestParam(name = "birthDay") String birthDay){
        return peopleService.getCustomerBirthDay(birthDay);
    }
    @GetMapping("/customers")
    public ResponseEntity<List<PeopleDTO>> getAllCustomers(){
        return peopleService.getAllCustomer();
    }



}
