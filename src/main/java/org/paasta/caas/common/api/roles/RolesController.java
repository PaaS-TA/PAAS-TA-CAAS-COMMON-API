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

    @GetMapping(value = "/{id:.+}")
    public List<Roles> getRoleList(@PathVariable("id") String id){
        return rolesService.getRoleList(id);
    }

    // Todo 추후 수정 필요.
    @GetMapping(value = "/{id:.+}/detail")
    public Roles getRole(@PathVariable("id") String id, @RequestParam("resourceCode") String resourceCode,  @RequestParam("verbCode") String verbCode){
        return rolesService.getRole(id, resourceCode, verbCode);
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
