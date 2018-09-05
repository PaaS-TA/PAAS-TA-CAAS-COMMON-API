package org.paasta.caas.common.api.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.util.List;

/**
 * User Repository 인터페이스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.02
 */
@NamedNativeQueries({
        @NamedNativeQuery(name = "user.deleteByServiceInstanceId",
                query = "DELETE FROM user WHERE service_instance_id = ?1")
})
@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByServiceInstanceIdAndOrganizationGuid(String serviceInstanceId, String organizationGuid);

    Users findByServiceInstanceIdAndOrganizationGuidAndUserId(String serviceInstanceId, String organizationGuid, String userId);

    Users deleteByServiceInstanceId(String serviceInstanceId);
}
