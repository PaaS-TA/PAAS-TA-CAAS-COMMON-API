package org.paasta.caas.common.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The interface User repository.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
