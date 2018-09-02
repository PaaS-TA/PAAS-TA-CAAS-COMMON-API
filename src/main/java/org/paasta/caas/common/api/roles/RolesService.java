package org.paasta.caas.common.api.roles;

import org.paasta.caas.common.api.common.CommonService;
import org.paasta.caas.common.api.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-08-27
 */
@Service
public class RolesService {

    private final CommonService commonService;
    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(CommonService commonService, RolesRepository rolesRepository) {
        this.commonService = commonService;
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> getRoleList(String id) {
        return rolesRepository.findByRoleSetCode(id);
    }


    public Roles getRole(String id, String resourceCode, String verbCode) {
        return rolesRepository.findByRoleSetCodeAndResourceCodeAndVerbCode(id, resourceCode, verbCode);
    }

    public Roles createRole(Roles role) {
        return rolesRepository.save(role);
    }


    public Roles deleteRole(Roles role) {
        rolesRepository.delete(role);
        return (Roles) commonService.setResultModel(Roles.class, Constants.RESULT_STATUS_SUCCESS);
    }
}
