package org.paasta.caas.common.api.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-08-27
 */
@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public List<Roles> getRoleList(){
        return rolesService.getRoleList();
    }

    // Todo 추후 수정 필요.
    @PostMapping(value = "/{id:.+}")
    public Roles getRole(@PathVariable("id") String id, @RequestBody Roles roles){
        return rolesService.getRole(id, roles.getResourceCode(), roles.getVerbCode());
    }

    @PostMapping
    public Roles createRole(@RequestBody Roles roles){
        return rolesService.createRole(roles);
    }

    @DeleteMapping
    public Roles deleteRole(@RequestBody Roles roles){
        return rolesService.deleteRole(roles);
    }
}
