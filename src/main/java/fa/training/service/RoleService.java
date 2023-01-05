package fa.training.service;


import fa.training.dto.RoleDTO;


public interface RoleService {

    RoleDTO findByID(long id);

    String addRole(RoleDTO roleDTO);

    String editRole(RoleDTO roleDTO);

}
