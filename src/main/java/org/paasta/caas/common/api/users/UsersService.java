package org.paasta.caas.common.api.users;

import org.paasta.caas.common.api.common.CommonService;
import org.paasta.caas.common.api.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);
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
     * user 목록을 조회한다.
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
     * ServiceInstanceId, OrganizationGuid 로 user 목록을 조회한다.
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
     * ServiceInstanceId, OrganizationGuid, userId 로 user 를 조회한다.
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
     * ServiceInstanceId 와 OrganizationGuid 으로 user 의 권한을 수정한다.
     *
     * @param user the user
     * @return the user
     */
    Users updateUserRoleByServiceInstanceIdAndOrganizationGuid(Users user) {
        return (Users) commonService.setResultModel(userRepository.save(user), Constants.RESULT_STATUS_SUCCESS);
    }

    /**
     * user 를 생성한다.
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
     * user 를 수정한다.
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
     * ServiceInstanceId 로 user 를 삭제한다.
     *
     * @param serviceInstanceId the serviceInstanceId
     */
    void deleteByServiceInstanceId(String serviceInstanceId) {
        userRepository.deleteByServiceInstanceId(serviceInstanceId);
    }

    /**
     * ServiceInstanceId 로 user 를 수정한다.
     *
     * @param serviceInstanceId the serviceInstanceId
     */
    void updateByServiceInstanceId(String serviceInstanceId, Users userInfo) {
        List<Users> userList = userRepository.findByServiceInstanceId(serviceInstanceId);
        for(Users user : userList) {
            user.setPlanName(userInfo.getPlanName());
            user.setPlanDescription(userInfo.getPlanDescription());
            userRepository.save(user);
        }
    }

    /**
     * namespace 로 user name 을 조회한다.
     *
     * @param namespace the namespace
     */
    boolean isUserByCaasNamespace(String userId, String namespace) {
        boolean isExistUser = true;

        Users users =  userRepository.findByUserIdAndCaasNamespace(userId, namespace);

        LOGGER.info("users is "+users);

        if(users == null ){
            LOGGER.info("findByCaasNamespace is NULL");
            isExistUser = false;
        }
        return isExistUser;
    }


    /**
     * ServiceInstanceId, OrganizationGuid, userId 로 User 를 삭제한다.
     *
     * @param serviceInstanceId the serviceInstanceId
     * @param organizationGuid the organizationGuid
     * @param userId the userId
     * @return the user
     */
    Users deleteByServiceInstanceIdAndOrganizationGuid(String serviceInstanceId, String organizationGuid, String userId) {
        int resultValue = userRepository.deleteByServiceInstanceIdAndOrganizationGuidAndUserId(serviceInstanceId, organizationGuid, userId);
        Users users = new Users();
        String resultCode;
        if(resultValue == 1){
            resultCode = Constants.RESULT_STATUS_SUCCESS;
        }else {
            resultCode = Constants.RESULT_STATUS_FAIL;
        }

        return (Users) commonService.setResultModel(users, resultCode);
    }
}
