package fa.training.service.impl;


import fa.training.dto.PeopleDTO;
import fa.training.entity.People;
import fa.training.mapper.PeopleMapper;
import fa.training.repository.PeopleRepository;
import fa.training.service.PeopleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final PeopleMapper peopleMapper;

    public PeopleServiceImpl(PeopleRepository peopleRepository, PeopleMapper peopleMapper) {
        this.peopleRepository = peopleRepository;
        this.peopleMapper = peopleMapper;
    }


    @Override
    public String addPeople(PeopleDTO peopleDTO) {
        People people = peopleMapper.castDTOToEntity(peopleDTO);
        try {
            peopleRepository.save(people);
            return "Add Complete";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public Boolean deletePeople(String peopleEmail) {
        try{
            People p = peopleRepository.findByEmail(peopleEmail).orElseThrow();
            peopleRepository.delete(p);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public String editPeople(PeopleDTO peopleDTO) {
        People people = peopleMapper.castDTOToEntity(peopleDTO);
        try {
            peopleRepository.saveAndFlush(people);
            return "Edit Complete";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public PeopleDTO findByEmail(String peopleEmail) {
        People people = peopleRepository.findByEmail(peopleEmail).orElseThrow();
        return peopleMapper.castEntityToDTO(people);
    }

    @Override
    public List<PeopleDTO> getAdmins() {
        List<People> people = peopleRepository.findByRole("ROLE_ADMIN");
        return peopleMapper.castListEntityToDTO(people);
    }

    @Override
    public List<PeopleDTO> getAllCustomer() {
        List<People> people =  peopleRepository.findByRole("ROLE_USER");
        return peopleMapper.castListEntityToDTO(people);
    }

    @Override
    public List<PeopleDTO> getCustomerBirthDay(String birthday) {
        List<People> people = peopleRepository.findByRoleAndBirthday("ROLE_USER",birthday);
        return peopleMapper.castListEntityToDTO(people);
    }


}
