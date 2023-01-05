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
    public ResponseEntity<?> addPeople(@RequestBody @Valid PeopleDTO peopleDTO) throws NullPointerException{
        return ResponseEntity.ok(peopleService.addPeople(peopleDTO));
    }
    @DeleteMapping("/del_person")
    public ResponseEntity<?> deleteCustomer(@RequestParam(name = "email") String email) {
        if(Boolean.TRUE.equals(peopleService.deletePeople(email))){
            return ResponseEntity.ok("Complete");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/edit_person")
    public ResponseEntity<?> editCustomer(@Valid @RequestBody PeopleDTO peopleDTO) throws NullPointerException{
        return ResponseEntity.ok(peopleService.editPeople(peopleDTO));
    }
    @GetMapping("/get_person")
    public ResponseEntity<PeopleDTO> getPerson(@RequestParam(name = "email") String email){
        if(peopleService.findByEmail(email)!=null)
            return ResponseEntity.ok(peopleService.findByEmail(email));
        else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/get_admin")
    public ResponseEntity<List<PeopleDTO>> getAdmin(){
        if(peopleService.getAdmins().size()>0)
            return ResponseEntity.ok(peopleService.getAdmins());
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get_birthday")
    public ResponseEntity<List<PeopleDTO>> getPersonBirthday(@RequestParam(name = "birthDay") String birthDay){
        if(peopleService.getCustomerBirthDay(birthDay) != null)
            return ResponseEntity.ok(peopleService.getCustomerBirthDay(birthDay));
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/customers")
    public ResponseEntity<List<PeopleDTO>> getAllCustomers(){
        if(peopleService.getAllCustomer().size()>0)
            return ResponseEntity.ok(peopleService.getAllCustomer());
        else {
            return ResponseEntity.notFound().build();
        }
    }



}
