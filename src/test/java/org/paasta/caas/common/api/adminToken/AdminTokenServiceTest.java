package org.paasta.caas.common.api.adminToken;

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

    private static final int PID = 1;
    private static final String TOKEN_NAME = "test-token-name";
    private static final String TOKEN_VALUE = "test-token-value";
    private static final String RESULT_CODE_SUCCESS = Constants.RESULT_STATUS_SUCCESS;

    private static AdminToken gTestModel = null;
    private static AdminToken gTestResultModel = null;

    @Mock
    private AdminTokenRepository adminTokenRepository;

    @Mock
    private CommonService commonService;

    @InjectMocks
    private AdminTokenService adminTokenService;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {

        gTestModel = new AdminToken();
        gTestResultModel = new AdminToken();

        gTestModel.setTokenName(TOKEN_NAME);
        gTestModel.setTokenValue(TOKEN_VALUE);

        gTestResultModel.setResultCode(RESULT_CODE_SUCCESS);
        gTestResultModel.setTokenName(TOKEN_NAME);
        gTestResultModel.setTokenValue(TOKEN_VALUE);

    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
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
        when(adminTokenRepository.getOne(TOKEN_NAME)).thenReturn(gTestResultModel);

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

}
