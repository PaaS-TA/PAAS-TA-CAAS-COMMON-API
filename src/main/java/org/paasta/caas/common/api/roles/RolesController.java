package org.paasta.caas.common.api.roles;

import org.paasta.caas.common.api.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Roles Controller 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-27
 */
@RestController
public class RolesController {

    private final RolesService rolesService;

    /**
     * Instantiates a new Roles controller
     *
     * @param rolesService the roles service
     */
    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    /**
     * Roles 의 목록을 조회한다.
     *
     * @param id  the id
     * @return the Roles List
     */
    @GetMapping(value = Constants.URI_API_ROLES_ID_LIST)
    public RolesList getRoleList(@PathVariable("id") String id){
        return rolesService.getRoleList(id);
    }


    /**
     * Roles 의 상세 정보를 조회한다.
     *
     * @param id the id
     * @param resourceCode the resourceCode
     * @param verbCode the verbCode
     * @return the Roles
     */
    @GetMapping(value = Constants.URI_API_ROLES_ID_DETAIL)
    public Roles getRole(@PathVariable("id") String id, @RequestParam("resourceCode") String resourceCode,  @RequestParam("verbCode") String verbCode){
        return rolesService.getRole(id, resourceCode, verbCode);
    }

    /**
     * Role 을 생성한다.
     *
     * @param roles the roles
     * @return the Roles
     */
    @PostMapping(value = Constants.URI_API_ROLES)
    public Roles createRole(@RequestBody Roles roles){
        return rolesService.createRole(roles);
    }

    /**
     * Role 을 삭제한다.
     *
     * @param roles the roles
     * @return the Roles
     */
    @DeleteMapping(value = Constants.URI_API_ROLES)
    public Roles deleteRole(@RequestBody Roles roles){
        return rolesService.deleteRole(roles);
    }
}
