package fa.training.controller;

import fa.training.dto.RoleDTO;
import fa.training.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/add_role")
    public ResponseEntity<RoleDTO> addRole(@Valid @RequestBody RoleDTO roleDTO) {
        return roleService.addRole(roleDTO);
    }
    @PostMapping("/edit_role")
    public ResponseEntity<RoleDTO> editRole(@Valid @RequestBody RoleDTO roleDTO) {
        return roleService.editRole(roleDTO);
    }

    @GetMapping("/get_role")
    public ResponseEntity<RoleDTO> getRole(@RequestParam("id") long roleId){
        return roleService.findByID(roleId);
    }

}
