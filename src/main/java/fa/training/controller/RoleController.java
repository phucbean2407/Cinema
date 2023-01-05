package fa.training.controller;

import fa.training.dto.RoleDTO;
import fa.training.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add_role")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.addRole(roleDTO));
    }
    @PostMapping("/edit_role")
    public ResponseEntity<?> editRole(@Valid @RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.editRole(roleDTO));
    }

    @GetMapping("/get_role")
    public ResponseEntity<RoleDTO> getRole(@RequestParam("id") long roleId){
        return ResponseEntity.ok(roleService.findByID(roleId));
    }

}
