package org.paasta.caas.common.api.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-08-27
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {
    Roles findByRoleSetCodeAndResourceCodeAndVerbCode(String id, @NotNull(message = "resource_code cannot be null") @NotEmpty(message = "resource_code is mandatory") String resourceCode, @NotNull(message = "verb_code cannot be null") @NotEmpty(message = "verb_code is mandatory") String verbCode);
}
