package org.paasta.caas.common.api.user;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class UserServiceTest {

    private static final int PID = 1;
    private static final String USER_ID = "test-id";
    private static final String SERVICE_INSTANCE_ID = "service-instance-id";
    private static final String CAAS_ACCOUNT_ACCESS_TOKEN = "account-access-token";
    private static final String CAAS_ACCOUNT_NAME = "account-name";
    private static final String ORGANIZATION_GUID = "caas-org";
    private static final String SPACE_GUID = "caas-space";

    private static final String ROLE_SET_CODE = "test-role";
    private static final String NAMESPACE = "kube-namespace";
    private static final String DESCRIPTION = "test-description";
    private static final String CREATED = "test-created";
    private static final String LAST_MODIFIED = "test-last-modified";
    private static final String RESULT_CODE_SUCCESS = Constants.RESULT_STATUS_SUCCESS;
    private static final String RESULT_MESSAGE = "test-result-message";
    private static final String RESULT_ERROR_MESSAGE = "test-result-error-message";

    private static User gTestModel = null;
    private static User gTestResultModel = null;
    private static User gTestResultErrorModel = null;
    private static List<User> gTestResultList = null;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommonService commonService;

    @InjectMocks
    private UserService userService;


    @Before
    public void setUp() throws Exception {

        gTestResultList = new ArrayList<>();
        gTestModel = new User();
        gTestResultModel = new User();
        gTestResultErrorModel = new User();

        gTestModel.setUserId(USER_ID);
        gTestModel.setServiceInstanceId(SERVICE_INSTANCE_ID);
        gTestModel.setCaasAccountAccessToken(CAAS_ACCOUNT_ACCESS_TOKEN);
        gTestModel.setCaasAccountName(CAAS_ACCOUNT_NAME);
        gTestModel.setOrganizationGuid(ORGANIZATION_GUID);
        gTestModel.setSpaceGuid(SPACE_GUID);
        gTestModel.setRoleSetCode(ROLE_SET_CODE);
        gTestModel.setNamespace(NAMESPACE);
        gTestModel.setDescription(DESCRIPTION);

        gTestResultModel.setResultCode(RESULT_CODE_SUCCESS);
        gTestResultModel.setResultMessage(RESULT_MESSAGE);
        gTestResultModel.setId(PID);
        gTestResultModel.setUserId(USER_ID);
        gTestResultModel.setServiceInstanceId(SERVICE_INSTANCE_ID);
        gTestResultModel.setCaasAccountAccessToken(CAAS_ACCOUNT_ACCESS_TOKEN);
        gTestResultModel.setCaasAccountName(CAAS_ACCOUNT_NAME);
        gTestResultModel.setOrganizationGuid(ORGANIZATION_GUID);
        gTestResultModel.setSpaceGuid(SPACE_GUID);
        gTestResultModel.setRoleSetCode(ROLE_SET_CODE);
        gTestResultModel.setNamespace(NAMESPACE);
        gTestResultModel.setDescription(DESCRIPTION);
        gTestResultModel.setCreated(CREATED);
        gTestResultModel.setLastModified(LAST_MODIFIED);

        gTestResultList.add(gTestResultModel);

        gTestResultErrorModel.setResultCode(Constants.RESULT_STATUS_FAIL);
    }

    @After
    public void tearDown() throws Exception {
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// MethodName_StateUnderTest_ExpectedBehavior
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    public void getUserList_Valid_ReturnList() {
        // CONDITION
        when(userRepository.findAll()).thenReturn(gTestResultList);

        // TEST
        List<User> resultList = userService.getUserList();

        // VERIFY
        assertThat(resultList).isNotNull();
        assertEquals(gTestResultList, resultList);
        assertEquals(PID, resultList.get(0).getId());
        assertEquals(USER_ID, resultList.get(0).getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultList.get(0).getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultList.get(0).getCaasAccountAccessToken());
        assertEquals(CAAS_ACCOUNT_NAME, resultList.get(0).getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultList.get(0).getOrganizationGuid());
        assertEquals(SPACE_GUID, resultList.get(0).getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultList.get(0).getRoleSetCode());
        assertEquals(NAMESPACE, resultList.get(0).getNamespace());
        assertEquals(DESCRIPTION, resultList.get(0).getDescription());
        assertEquals(CREATED, resultList.get(0).getCreated());
        assertEquals(LAST_MODIFIED, resultList.get(0).getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultList.get(0).getResultCode());
        assertEquals(RESULT_MESSAGE, resultList.get(0).getResultMessage());
    }

    @Test
    public void getUser_Valid_ReturnModel() {
        // CONDITION
        when(userRepository.getOne((long) PID)).thenReturn(gTestResultModel);

        // TEST
        User resultModel = userService.getUser(PID);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(PID, resultModel.getId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultModel.getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultModel.getCaasAccountAccessToken());
        assertEquals(CAAS_ACCOUNT_NAME, resultModel.getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultModel.getOrganizationGuid());
        assertEquals(SPACE_GUID, resultModel.getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
        assertEquals(NAMESPACE, resultModel.getNamespace());
        assertEquals(DESCRIPTION, resultModel.getDescription());
        assertEquals(CREATED, resultModel.getCreated());
        assertEquals(LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
        assertEquals(RESULT_MESSAGE, resultModel.getResultMessage());
    }

    @Test
    public void createUser_Valid_ReturnModel() {
        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_SUCCESS);
        when(userRepository.save(gTestModel)).thenReturn(gTestResultModel);

        // TEST
        User resultModel = userService.createUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(PID, resultModel.getId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultModel.getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultModel.getCaasAccountAccessToken());
        assertEquals(CAAS_ACCOUNT_NAME, resultModel.getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultModel.getOrganizationGuid());
        assertEquals(SPACE_GUID, resultModel.getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
        assertEquals(NAMESPACE, resultModel.getNamespace());
        assertEquals(DESCRIPTION, resultModel.getDescription());
        assertEquals(CREATED, resultModel.getCreated());
        assertEquals(LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
        assertEquals(RESULT_MESSAGE, resultModel.getResultMessage());
    }

    @Test
    public void createUser_InvalidModel_ReturnErrorModel() {
        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_FAIL);
        when(commonService.setResultModel(User.class, Constants.RESULT_STATUS_FAIL, Constants.RESULT_STATUS_FAIL)).thenReturn(gTestResultErrorModel);

        // TEST
        User resultModel = userService.createUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultModel.getResultCode());
    }

    @Test
    public void updateUser_Valid_ReturnModel() {
        // SET
        gTestModel.setId(PID);

        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_SUCCESS);
        when(userRepository.save(gTestModel)).thenReturn(gTestResultModel);

        // TEST
        User resultModel = userService.updateUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(PID, resultModel.getId());
        assertEquals(USER_ID, resultModel.getUserId());
        assertEquals(SERVICE_INSTANCE_ID, resultModel.getServiceInstanceId());
        assertEquals(CAAS_ACCOUNT_ACCESS_TOKEN, resultModel.getCaasAccountAccessToken());
        assertEquals(CAAS_ACCOUNT_NAME, resultModel.getCaasAccountName());
        assertEquals(ORGANIZATION_GUID, resultModel.getOrganizationGuid());
        assertEquals(SPACE_GUID, resultModel.getSpaceGuid());
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
        assertEquals(NAMESPACE, resultModel.getNamespace());
        assertEquals(DESCRIPTION, resultModel.getDescription());
        assertEquals(CREATED, resultModel.getCreated());
        assertEquals(LAST_MODIFIED, resultModel.getLastModified());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
        assertEquals(RESULT_MESSAGE, resultModel.getResultMessage());
    }

    @Test
    public void updateUser_InvalidModel_ReturnErrorModel() {
        // SET
        gTestModel.setId(PID);

        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_FAIL);
        when(commonService.setResultModel(User.class, Constants.RESULT_STATUS_FAIL, Constants.RESULT_STATUS_FAIL)).thenReturn(gTestResultErrorModel);

        // TEST
        User resultModel = userService.updateUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultModel.getResultCode());
    }

    @Test
    public void deleteUser_Valid_ReturnModel() {
        // SET
        gTestModel.setId(PID);
        gTestResultErrorModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        // CONDITION
        doNothing().when(userRepository).delete(gTestModel);
        when(commonService.setResultModel(User.class, Constants.RESULT_STATUS_SUCCESS, "")).thenReturn(gTestResultErrorModel);

        // TEST
        User resultModel = userService.deleteUser(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }
}