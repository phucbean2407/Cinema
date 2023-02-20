package fa.training.service;


import fa.training.dto.RoleDTO;


public interface RoleService {

    RoleDTO findByAdmin();

    RoleDTO findByUser();

    String addRole(RoleDTO roleDTO);

    String editRole(RoleDTO roleDTO);

}
