package org.paasta.caas.common.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.02
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
    public UsersController(UsersService userService) {this.userService = userService;}

    /**
     * Gets user list.
     *
     * @return the user list
     */
    @GetMapping
    List<Users> getUserList() {
        return userService.getUserList();
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @GetMapping(value = "/{id:.+}")
    Users getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    /**
     * Gets user list.
     *
     * @param serviceInstanceId the serviceInstanceId
     * @param organizationGuid the organizationGuid
     * @return the user
     */
    @GetMapping(value = "/serviceInstanceId/{serviceInstanceId:.+}/organizationGuid/{organizationGuid:.+}")
    List<Users> getUsersByServiceInstanceIdAndOrganizationGuid(@PathVariable("serviceInstanceId") String serviceInstanceId, @PathVariable("organizationGuid") String organizationGuid) {
        return userService.getUsersByServiceInstanceIdAndOrganizationGuid(serviceInstanceId, organizationGuid);
    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    @PostMapping
    Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    /**
     * Update user user.
     *
     * @param user the user
     * @return the user
     */
    @PutMapping
    Users updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    /**
     * Delete user string.
     *
     * @param user the user
     * @return the string
     */
    @DeleteMapping
    Users deleteUser(@RequestBody Users user) {
        return userService.deleteUser(user);
    }
}























