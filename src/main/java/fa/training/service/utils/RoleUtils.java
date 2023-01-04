package fa.training.service.utils;

import fa.training.dto.RoleDTO;
import fa.training.entity.Role;
import fa.training.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleUtils {
    static RoleRepository roleRepository;

    public static RoleDTO castEntityToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        return roleDTO;
    }


    public static Role castDTOToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }

}
