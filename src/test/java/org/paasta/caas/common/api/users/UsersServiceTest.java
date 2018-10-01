package org.paasta.caas.common.api.users;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.paasta.caas.common.api.common.CommonService;
import org.paasta.caas.common.api.common.Constants;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * The type Users service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class UsersServiceTest {

    private final int PID = 1;
    private final String USER_ID = "test-id";
    private final String SERVICE_INSTANCE_ID = "test-service-instance-id";
    private final String CAAS_ACCOUNT_ACCESS_TOKEN = "test-account-access-token";
    private final String CAAS_ACCOUNT_NAME = "test-account-name";
    private final String ORGANIZATION_GUID = "test-caas-org";
    private final String SPACE_GUID = "test-caas-space";

    private final String ROLE_SET_CODE = "test-role";
    private final String NAMESPACE = "test-kube-namespace";
    private final String DESCRIPTION = "test-description";
    private final String CREATED = "test-created";
    private final String LAST_MODIFIED = "test-last-modified";
    private final String RESULT_CODE_SUCCESS = Constants.RESULT_STATUS_SUCCESS;

    private Users gTestModel = null;
    private Users gTestResultModel = null;
    private Users gTestFinalModel = null;
    private Users gTestResultErrorModel = null;
    private List<Users> gTestResultList = null;

    @Mock
    private UsersRepository userRepository;

    @Mock
    private CommonService commonService;

    @InjectMocks
    private UsersService userService;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {

        gTestResultList = new ArrayList<>();
        gTestModel = new Users();
        gTestResultModel = new Users();
        gTestFinalModel = new Users();
        gTestResultErrorModel = new Users();

        gTestModel.setUserId(USER_ID);
        gTestModel.setServiceInstanceId(SERVICE_INSTANCE_ID);
        gTestModel.setCaasAccountTokenName(CAAS_ACCOUNT_ACCESS_TOKEN);
        gTestModel.setCaasAccountName(CAAS_ACCOUNT_NAME);
        gTestModel.setOrganizationGuid(ORGANIZATION_GUID);
        gTestModel.setSpaceGuid(SPACE_GUID);
        gTestModel.setRoleSetCode(ROLE_SET_CODE);
        gTestModel.setCaasNamespace(NAMESPACE);
        gTestModel.setDescription(DESCRIPTION);

        gTestResultModel.setResultCode(RESULT_CODE_SUCCESS);
        gTestResultModel.setId(PID);
        gTestResultModel.setUserId(USER_ID);
        gTestResultModel.setServiceInstanceId(SERVICE_INSTANCE_ID);
        gTestResultModel.setCaasAccountTokenName(CAAS_ACCOUNT_ACCESS_TOKEN);
        gTestResultModel.setCaasAccountName(CAAS_ACCOUNT_NAME);
        gTestResultModel.setOrganizationGuid(ORGANIZATION_GUID);
        gTestResultModel.setSpaceGuid(SPACE_GUID);
        gTestResultModel.setRoleSetCode(ROLE_SET_CODE);
        gTestResultModel.setCaasNamespace(NAMESPACE);
        gTestResultModel.setDescription(DESCRIPTION);
        gTestResultModel.setCreated(CREATED);
        gTestResultModel.setLastModified(LAST_MODIFIED);

        gTestResultList.add(gTestResultModel);

        gTestFinalModel.setRoleSetCode(RESULT_CODE_SUCCESS);
        gTestFinalModel.setId(PID);
        gTestFinalModel.setUserId(USER_ID);
        gTestFinalModel.setServiceInstanceId(SERVICE_INSTANCE_ID);
        gTestFinalModel.setCaasAccountTokenName(CAAS_ACCOUNT_ACCESS_TOKEN);
        gTestFinalModel.setCaasAccountName(CAAS_ACCOUNT_NAME);
        gTestFinalModel.setOrganizationGuid(ORGANIZATION_GUID);
        gTestFinalModel.setSpaceGuid(SPACE_GUID);
        gTestFinalModel.setRoleSetCode(ROLE_SET_CODE);
        gTestFinalModel.setCaasNamespace(NAMESPACE);
        gTestFinalModel.setDescription(DESCRIPTION);
        gTestFinalModel.setCreated(CREATED);
        gTestFinalModel.setLastModified(LAST_MODIFIED);
        gTestFinalModel.setResultCode(RESULT_CODE_SUCCESS);

        gTestResultErrorModel.setResultCode(Constants.RESULT_STATUS_FAIL);
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// MethodName_StateUnderTest_ExpectedBehavior
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Gets user list valid return list.
     */
    @Test
    public void getUserList_Valid_ReturnList() {
        // CONDITION
        when(userRepository.findAll()).thenReturn(gTestResultList);

        // TEST
        List<Users> resultList = userService.getUserList();

        // VERIFY
        assertThat(resultList).isNotNull();
        assertEquals(gTestResultList, resultList);
        assertEquals(PID, resultList.get(0).getId());
        assertEquals(USER_ID, resultList.get(0).getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultList.get(0).getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultList.get(0).getCaasAccountTokenName());
        assertEquals(CAAS_ACCOUNT_NAME, resultList.get(0).getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultList.get(0).getOrganizationGuid());
        assertEquals(SPACE_GUID, resultList.get(0).getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultList.get(0).getRoleSetCode());
        assertEquals(NAMESPACE, resultList.get(0).getCaasNamespace());
        assertEquals(DESCRIPTION, resultList.get(0).getDescription());
        assertEquals(CREATED, resultList.get(0).getCreated());
        assertEquals(LAST_MODIFIED, resultList.get(0).getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultList.get(0).getResultCode());
    }

    @Test
    public void getUsersByServiceInstanceIdAndOrganizationGuid_Valid_ReturnModel(){
        // CONDITION
        when(userRepository.findByServiceInstanceIdAndOrganizationGuid(SERVICE_INSTANCE_ID, ORGANIZATION_GUID)).thenReturn(gTestResultList);

        // TEST
        UsersList resultList = userService.getUsersByServiceInstanceIdAndOrganizationGuid(SERVICE_INSTANCE_ID, ORGANIZATION_GUID);

        // VERIFY
        assertThat(resultList.getItems()).isNotNull();
        assertEquals(gTestResultList, resultList.getItems());
        assertEquals(PID, resultList.getItems().get(0).getId());
        assertEquals(USER_ID, resultList.getItems().get(0).getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultList.getItems().get(0).getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultList.getItems().get(0).getCaasAccountTokenName());
        assertEquals(CAAS_ACCOUNT_NAME, resultList.getItems().get(0).getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultList.getItems().get(0).getOrganizationGuid());
        assertEquals(SPACE_GUID, resultList.getItems().get(0).getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultList.getItems().get(0).getRoleSetCode());
        assertEquals(NAMESPACE, resultList.getItems().get(0).getCaasNamespace());
        assertEquals(DESCRIPTION, resultList.getItems().get(0).getDescription());
        assertEquals(CREATED, resultList.getItems().get(0).getCreated());
        assertEquals(LAST_MODIFIED, resultList.getItems().get(0).getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultList.getItems().get(0).getResultCode());
    }

    /**
     * Gets user valid return model.
     */
    @Test
    public void getUser_Valid_ReturnModel() {
        // CONDITION
        when(userRepository.getOne((long) PID)).thenReturn(gTestResultModel);

        // TEST
        Users resultModel = userService.getUser(PID);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(PID, resultModel.getId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultModel.getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultModel.getCaasAccountTokenName());
        assertEquals(CAAS_ACCOUNT_NAME, resultModel.getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultModel.getOrganizationGuid());
        assertEquals(SPACE_GUID, resultModel.getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
        assertEquals(NAMESPACE, resultModel.getCaasNamespace());
        assertEquals(DESCRIPTION, resultModel.getDescription());
        assertEquals(CREATED, resultModel.getCreated());
        assertEquals(LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }

    @Test
    public void getUserByServiceInstanceIdAndOrganizationGuid_Valid_ReturnModel(){
        // CONDITION
        when(userRepository.findByServiceInstanceIdAndOrganizationGuidAndUserId(SERVICE_INSTANCE_ID, ORGANIZATION_GUID, USER_ID)).thenReturn(gTestResultModel);
        when(commonService.setResultModel(gTestResultModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gTestFinalModel);

        // TEST
        Users resultModel = userService.getUserByServiceInstanceIdAndOrganizationGuid(SERVICE_INSTANCE_ID, ORGANIZATION_GUID, USER_ID);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(PID, resultModel.getId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultModel.getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultModel.getCaasAccountTokenName());
        assertEquals(CAAS_ACCOUNT_NAME, resultModel.getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultModel.getOrganizationGuid());
        assertEquals(SPACE_GUID, resultModel.getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
        assertEquals(NAMESPACE, resultModel.getCaasNamespace());
        assertEquals(DESCRIPTION, resultModel.getDescription());
        assertEquals(CREATED, resultModel.getCreated());
        assertEquals(LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }

    /**
     * Create user valid return model.
     */
    @Test
    public void createUser_Valid_ReturnModel() {
        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_SUCCESS);
        when(userRepository.save(gTestModel)).thenReturn(gTestResultModel);

        // TEST
        Users resultModel = userService.createUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(PID, resultModel.getId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultModel.getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultModel.getCaasAccountTokenName());
        assertEquals(CAAS_ACCOUNT_NAME, resultModel.getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultModel.getOrganizationGuid());
        assertEquals(SPACE_GUID, resultModel.getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
        assertEquals(NAMESPACE, resultModel.getCaasNamespace());
        assertEquals(DESCRIPTION, resultModel.getDescription());
        assertEquals(CREATED, resultModel.getCreated());
        assertEquals(LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Create user invalid model return error model.
     */
    @Test
    public void createUser_InvalidModel_ReturnErrorModel() {
        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_FAIL);
        when(commonService.setResultModel(Users.class, Constants.RESULT_STATUS_FAIL)).thenReturn(gTestResultErrorModel);

        // TEST
        Users resultModel = userService.createUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultModel.getResultCode());
    }


    /**
     * Update user valid return model.
     */
    @Test
    public void updateUser_Valid_ReturnModel() {
        // SET
        gTestModel.setId(PID);

        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_SUCCESS);
        when(userRepository.save(gTestModel)).thenReturn(gTestResultModel);

        // TEST
        Users resultModel = userService.updateUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(PID, resultModel.getId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultModel.getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultModel.getCaasAccountTokenName());
        assertEquals(CAAS_ACCOUNT_NAME, resultModel.getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultModel.getOrganizationGuid());
        assertEquals(SPACE_GUID, resultModel.getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
        assertEquals(NAMESPACE, resultModel.getCaasNamespace());
        assertEquals(DESCRIPTION, resultModel.getDescription());
        assertEquals(CREATED, resultModel.getCreated());
        assertEquals(LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }

    @Test
    public void updateUserRoleByServiceInstanceIdAndOrganizationGuid_Valid_Return_Model(){
        // SET
        gTestModel.setUserId(USER_ID);
        gTestModel.setRoleSetCode(ROLE_SET_CODE);

        // CONDITION
        when(userRepository.save(gTestModel)).thenReturn(gTestResultModel);
        when(commonService.setResultModel(gTestResultModel, RESULT_CODE_SUCCESS)).thenReturn(gTestFinalModel);

        // TEST
        Users resultModel = userService.updateUserRoleByServiceInstanceIdAndOrganizationGuid(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(PID, resultModel.getId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultModel.getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultModel.getCaasAccountTokenName());
        assertEquals(CAAS_ACCOUNT_NAME, resultModel.getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultModel.getOrganizationGuid());
        assertEquals(SPACE_GUID, resultModel.getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
        assertEquals(NAMESPACE, resultModel.getCaasNamespace());
        assertEquals(DESCRIPTION, resultModel.getDescription());
        assertEquals(CREATED, resultModel.getCreated());
        assertEquals(LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }

    /**
     * Update user invalid model return error model.
     */
    @Test
    public void updateUser_InvalidModel_ReturnErrorModel() {
        // SET
        gTestModel.setId(PID);

        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_FAIL);
        when(commonService.setResultModel(Users.class, Constants.RESULT_STATUS_FAIL)).thenReturn(gTestResultErrorModel);

        // TEST
        Users resultModel = userService.updateUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultModel.getResultCode());
    }


    /**
     * Delete user valid return model.
     */
    @Test
    public void deleteUser_Valid_ReturnModel() {
        // SET
        gTestModel.setId(PID);

        // CONDITION
        doNothing().when(userRepository).delete(gTestModel);
        when(commonService.setResultModel(gTestModel, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gTestResultModel);

        // TEST
        Users resultModel = userService.deleteUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }

    @Test
    public void deleteByServiceInstanceId_Valid_ReturnModel(){
        // SET
        gTestModel.setServiceInstanceId(SERVICE_INSTANCE_ID);

        // CONDITION
        when(userRepository.deleteByServiceInstanceId(gTestModel.getServiceInstanceId())).thenReturn(1);
        when(commonService.setResultModel(Users.class, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gTestResultModel);

        // TEST
        userService.deleteByServiceInstanceId(gTestModel.getServiceInstanceId());
    }

}
