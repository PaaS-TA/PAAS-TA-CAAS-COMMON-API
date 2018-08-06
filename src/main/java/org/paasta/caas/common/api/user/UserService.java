package org.paasta.caas.common.api.user;

import org.paasta.caas.common.api.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.02
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    /**
     * Gets user list.
     *
     * @return the user list
     */
    List<User> getUserList() {
        return userRepository.findAll();
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    User getUser(int id) {
        return userRepository.getOne((long) id);
    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Update user user.
     *
     * @param user the user
     * @return the user
     */
    User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Delete user string.
     *
     * @param user the user
     * @return the string
     */
    String deleteUser(User user) {
        userRepository.delete(user);
        return Constants.RESULT_STATUS_SUCCESS;
    }
}
