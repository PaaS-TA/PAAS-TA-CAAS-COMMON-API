package org.paasta.caas.common.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Repository 인터페이스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.02
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
