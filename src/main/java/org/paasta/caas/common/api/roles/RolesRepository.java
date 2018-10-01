package org.paasta.caas.common.api.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Roles JPA Repository 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-27
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {

    /**
     * Role 의 상세 정보를 조회한다.
     *
     * @param id the id
     * @param resourceCode the resource code
     * @param verbCode the verb code
     * @return Roles
     */
    Roles findByRoleSetCodeAndResourceCodeAndVerbCode(String id, String resourceCode, String verbCode);

    /**
     * Role 의 목록을 조회한다.
     *
     * @param id the id
     * @return the Roles List
     */
    List<Roles> findByRoleSetCode(String id);
}
