package fa.training.service;


import fa.training.dto.RoleDTO;
import fa.training.entity.Role;
import org.springframework.http.ResponseEntity;


public interface RoleService {

    ResponseEntity<RoleDTO> findByID(long id);

    ResponseEntity<RoleDTO> addRole(RoleDTO roleDTO);

    ResponseEntity<RoleDTO> editRole(RoleDTO roleDTO);

    RoleDTO castEntityToDTO(Role role);
    Role castDTOToEntity(RoleDTO roleDTO);
}
