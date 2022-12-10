package fa.training.service.impl;

import fa.training.dto.RoleDTO;
import fa.training.entity.Role;
import fa.training.repository.RoleRepository;
import fa.training.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<RoleDTO> findByID(long id) {
        Role role = roleRepository.findById(id).get();
        RoleDTO roleDTO = this.castEntityToDTO(role);
        return new ResponseEntity<>(roleDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoleDTO> addRole(RoleDTO roleDTO) {
        Role role = this.castDTOToEntity(roleDTO);
        try{
            roleRepository.save(role);
            return new ResponseEntity<>(roleDTO,HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<RoleDTO> editRole(RoleDTO roleDTO) {
        Role role = this.castDTOToEntity(roleDTO);
        try{
            roleRepository.saveAndFlush(role);
            RoleDTO roleDto = this.castEntityToDTO(roleRepository.findByName(role.getName()).get());
            return new ResponseEntity<>(roleDto,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }

    @Override
    public RoleDTO castEntityToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    @Override
    public Role castDTOToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        if(roleRepository.findByName(roleDTO.getName()) != null) {
            role = roleRepository.findByName(roleDTO.getName()).get();
        }
        role.setName(roleDTO.getName());
        return role;
    }


}
