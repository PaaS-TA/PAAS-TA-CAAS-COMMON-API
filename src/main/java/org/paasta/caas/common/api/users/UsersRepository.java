package org.paasta.caas.common.api.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User Repository 인터페이스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.02
 */
@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * User 의 목록을 조회한다.
     *
     * @param serviceInstanceId the service instance id
     * @param organizationGuid the organization id
     * @return the User List
     */
    List<Users> findByServiceInstanceIdAndOrganizationGuid(String serviceInstanceId, String organizationGuid);

    /**
     * User 의 상세 정보를 조회한다.
     *
     * @param serviceInstanceId the service instance id
     * @param organizationGuid the organization id
     * @param userId the user id
     * @return the user
     */
    Users findByServiceInstanceIdAndOrganizationGuidAndUserId(String serviceInstanceId, String organizationGuid, String userId);

    /**
     * User 를 삭제한다.
     *
     * @param serviceInstanceId the service instance id
     * @return Integer
     */
    @Modifying
    @Query(value = "delete from user where service_instance_id = ?1", nativeQuery = true)
    Integer deleteByServiceInstanceId(String serviceInstanceId);
}
