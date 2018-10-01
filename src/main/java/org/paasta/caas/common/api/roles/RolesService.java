package org.paasta.caas.common.api.roles;

import org.paasta.caas.common.api.common.CommonService;
import org.paasta.caas.common.api.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Roles Service 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-27
 */
@Service
public class RolesService {

    private final CommonService commonService;
    private final RolesRepository rolesRepository;

    /**
     * Instantiates a new Roles service
     *
     * @param commonService the common service
     * @param rolesRepository the role repository
     */
    @Autowired
    public RolesService(CommonService commonService, RolesRepository rolesRepository) {
        this.commonService = commonService;
        this.rolesRepository = rolesRepository;
    }

    /**
     * Role 목록을 조회한다.
     *
     * @param id the id
     * @return the Roles List
     */
    public RolesList getRoleList(String id) {
        List<Roles> roles = rolesRepository.findByRoleSetCode(id);

        RolesList rolesList = new RolesList();
        rolesList.setItems(roles);
        rolesList.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        return rolesList;
    }


    /**
     * Role 상세 정보를 조회한다.
     *
     * @param id the id
     * @param resourceCode the resourceCode
     * @param verbCode the verbCode
     * @return the Roles
     */
    public Roles getRole(String id, String resourceCode, String verbCode) {
        return rolesRepository.findByRoleSetCodeAndResourceCodeAndVerbCode(id, resourceCode, verbCode);
    }


    /**
     * Role 을 생성한다.
     *
     * @param role the role
     * @return the Roles
     */
    public Roles createRole(Roles role) {
        return rolesRepository.save(role);
    }


    /**
     * Role 을 삭제한다.
     *
     * @param role the role
     * @return the Roles
     */
    public Roles deleteRole(Roles role) {
        rolesRepository.delete(role);
        return (Roles) commonService.setResultModel(Roles.class, Constants.RESULT_STATUS_SUCCESS);
    }
}
