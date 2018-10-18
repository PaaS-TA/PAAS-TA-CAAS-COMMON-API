package org.paasta.caas.common.api.adminToken;

import org.assertj.core.api.Assertions;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Admin token service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class AdminTokenServiceTest {

    private final String TOKEN_NAME = "test-token-name";
    private final String TOKEN_VALUE = "test-token-value";
    private final String RESULT_CODE_SUCCESS = Constants.RESULT_STATUS_SUCCESS;

    private AdminToken gTestModel = null;
    private AdminToken gTestResultModel = null;
    private AdminToken gTestResultErrorModel = null;

    @Mock
    private AdminTokenRepository adminTokenRepository;

    @Mock
    private CommonService commonService;

    @InjectMocks
    private AdminTokenService adminTokenService;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {

        gTestModel = new AdminToken();
        gTestResultModel = new AdminToken();
        gTestResultErrorModel = new AdminToken();

        gTestModel.setTokenName(TOKEN_NAME);
        gTestModel.setTokenValue(TOKEN_VALUE);

        gTestResultModel.setResultCode(RESULT_CODE_SUCCESS);
        gTestResultModel.setTokenName(TOKEN_NAME);
        gTestResultModel.setTokenValue(TOKEN_VALUE);

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
     * Gets token value valid return model.
     */
    @Test
    public void getTokenValue_Valid_ReturnModel() {
        // CONDITION
        when(adminTokenRepository.findByTokenName(TOKEN_NAME)).thenReturn(gTestResultModel);

        // TEST
        AdminToken resultModel = adminTokenService.getTokenValue(TOKEN_NAME);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(TOKEN_NAME, resultModel.getTokenName());
        assertEquals(TOKEN_VALUE, resultModel.getTokenValue());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Create admin token valid return model.
     */
    @Test
    public void createAdminToken_Valid_ReturnModel() {
        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_SUCCESS);
        when(adminTokenRepository.save(gTestModel)).thenReturn(gTestResultModel);

        // TEST
        AdminToken resultModel = adminTokenService.createAdminToken(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(TOKEN_NAME, resultModel.getTokenName());
        assertEquals(TOKEN_VALUE, resultModel.getTokenValue());
        assertEquals(RESULT_CODE_SUCCESS, resultModel.getResultCode());
    }


    /**
     * Create admin token invalid model return error model.
     */
    @Test
    public void createAdminToken_InvalidModel_ReturnErrorModel() {
        // CONDITION
        when(commonService.procValidator(gTestModel)).thenReturn(Constants.RESULT_STATUS_FAIL);
        when(commonService.setResultModel(AdminToken.class, Constants.RESULT_STATUS_FAIL)).thenReturn(gTestResultErrorModel);

        // TEST
        AdminToken resultModel = adminTokenService.createAdminToken(gTestModel);

        // VERIFY
        Assertions.assertThat(resultModel).isNotNull();
        assertEquals(Constants.RESULT_STATUS_FAIL, resultModel.getResultCode());
    }

}
