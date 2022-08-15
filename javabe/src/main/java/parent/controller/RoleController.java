package parent.controller;

import parent.datamodel.Role;
import parent.exceptions.BadRequestAlertException;
import parent.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parent.repo.RoleRepo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/netflix")
public class RoleController {
    @Autowired
    private RoleRepo roleRepo;

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) throws URISyntaxException {
        if (role.getId() != null) {
            throw new BadRequestAlertException("A new role cannot already have an ID");
        }
        Role result = roleRepo.createRole(role);
        return ResponseEntity.created(new URI("/netflix/roles/" + result.getId()))
                .body(result);
    }


    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleRepo.getRoles();
    }



    //get roles by role id
    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long id) {
        Role role = roleRepo.getById(id);
        if(!role.getId().equals(id)){
            throw new ResourceNotFoundException("Not found Role with id = " + id);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        Role role = roleRepo.getById(id);
        if(!role.getId().equals(id)){
            throw new ResourceNotFoundException("Not found Role with id = " + id);
        }
        else{
            roleRepo.deleteByName(role.getName());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
