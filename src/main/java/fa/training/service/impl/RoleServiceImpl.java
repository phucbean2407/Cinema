package fa.training.service.impl;

import fa.training.dto.RoleDTO;
import fa.training.entity.Role;
import fa.training.mapper.RoleMapper;
import fa.training.repository.RoleRepository;
import fa.training.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO findByID(long id) {
        Role role = roleRepository.findById(id).get();
        return  roleMapper.castEntityToDTO(role);

    }

    @Override
    public String addRole(RoleDTO roleDTO) {
        Role role = roleMapper.castDTOToEntity(roleDTO);
        try{
            roleRepository.save(role);
            return "Add Complete";
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String editRole(RoleDTO roleDTO) {
        Role role = roleMapper.castDTOToEntity(roleDTO);
        try {
            roleRepository.saveAndFlush(role);
            return "Edit Complete";
        } catch (Exception e) {
            return e.getMessage();
        }
    }



}
