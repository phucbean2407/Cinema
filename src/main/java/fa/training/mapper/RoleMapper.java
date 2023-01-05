package fa.training.mapper;

import fa.training.dto.RoleDTO;
import fa.training.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDTO castEntityToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        return roleDTO;
    }


    public Role castDTOToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }

}
