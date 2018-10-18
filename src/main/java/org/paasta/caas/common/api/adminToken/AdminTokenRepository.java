package org.paasta.caas.common.api.adminToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Service Instance JPA Repository 클래스
 * @author Hyerin
 * @since 2018.07.24
 * @version 20180725
 */
@Repository
public interface AdminTokenRepository extends CrudRepository<AdminToken, String> {

    AdminToken findByTokenName(String tokenName);

}
