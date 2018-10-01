package org.paasta.caas.common.api.users;

import org.paasta.caas.common.api.common.CommonService;
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
public class UsersService {

    private final CommonService commonService;
    private final UsersRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param commonService  the common service
     * @param userRepository the user repository
     */
    @Autowired
    public UsersService(CommonService commonService, UsersRepository userRepository) {
        this.commonService = commonService;
        this.userRepository = userRepository;}

    /**
     * Gets user list.
     *
     * @return the user list
     */
    List<Users> getUserList() {
        return userRepository.findAll();
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    Users getUser(int id) {
        return userRepository.getOne((long) id);
    }

    /**
     * Gets user list By ServiceInstanceId, OrganizationGuid
     *
     * @param serviceInstanceId the serviceInstanceId
     * @param organizationGuid the organizationGuid
     * @return the user
     */
    UsersList getUsersByServiceInstanceIdAndOrganizationGuid(String serviceInstanceId, String organizationGuid) {
        List<Users> users = userRepository.findByServiceInstanceIdAndOrganizationGuid(serviceInstanceId, organizationGuid);

        UsersList usersList = new UsersList();
        usersList.setItems(users);
        usersList.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        return usersList;
    }


    /**
     * Gets user By ServiceInstanceId, OrganizationGuid, userId
     *
     * @param serviceInstanceId the serviceInstanceId
     * @param organizationGuid the organizationGuid
     * @param userId the userId
     * @return the user
     */
    Users getUserByServiceInstanceIdAndOrganizationGuid(String serviceInstanceId, String organizationGuid, String userId) {
        return (Users) commonService.setResultModel(userRepository.findByServiceInstanceIdAndOrganizationGuidAndUserId(serviceInstanceId, organizationGuid, userId), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Update User Role By ServiceInstanceId And OrganizationGuid
     *
     * @param user the user
     * @return the user
     */
    Users updateUserRoleByServiceInstanceIdAndOrganizationGuid(Users user) {
        return (Users) commonService.setResultModel(userRepository.save(user), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    Users createUser(Users user) {
        String result = commonService.procValidator(user);

        if (result.equals(Constants.RESULT_STATUS_SUCCESS)) {
            return userRepository.save(user);
        } else {
            return (Users) commonService.setResultModel(Users.class, Constants.RESULT_STATUS_FAIL);
        }
    }

    /**
     * Update user user.
     *
     * @param user the user
     * @return the user
     */
    Users updateUser(Users user) {
        String result = commonService.procValidator(user);

        if (result.equals(Constants.RESULT_STATUS_SUCCESS)) {
            return userRepository.save(user);
        } else {
            return (Users) commonService.setResultModel(Users.class, Constants.RESULT_STATUS_FAIL);
        }
    }

    /**
     * Delete user string.
     *
     * @param user the user
     * @return the string
     */
    Users deleteUser(Users user) {
        userRepository.delete(user);
        return (Users) commonService.setResultModel(user, Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * Delete By ServiceInstanceId.
     *
     * @param serviceInstanceId the serviceInstanceId
     */
    void deleteByServiceInstanceId(String serviceInstanceId) {
        userRepository.deleteByServiceInstanceId(serviceInstanceId);
    }

}
