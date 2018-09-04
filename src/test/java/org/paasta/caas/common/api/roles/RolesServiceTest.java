package org.paasta.caas.common.api.roles;

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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * The type Roles service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.yml")
public class RolesServiceTest {

    private final String ROLE_SET_CODE = "test-role-set-code";
    private final String RESOURCE_CODE = "test-resource-code";
    private final String VERB_CODE = "test-verb-code";
    private final String DESCRIPTION = "test-description";
    private final String CREATED = "test-created";

    private Roles gTestModel = null;
    private Roles gTestResultModel = null;
    private List<Roles> gTestResultList = null;

    @Mock
    private RolesRepository rolesRepository;

    @Mock
    private CommonService commonService;

    @InjectMocks
    private RolesService rolesService;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {

        gTestResultList = new ArrayList<>();
        gTestModel = new Roles();
        gTestResultModel = new Roles();

        gTestModel.setResourceCode(RESOURCE_CODE);
        gTestModel.setVerbCode(VERB_CODE);
        gTestModel.setDescription(DESCRIPTION);

        gTestResultModel.setRoleSetCode(ROLE_SET_CODE);
        gTestResultModel.setResourceCode(RESOURCE_CODE);
        gTestResultModel.setVerbCode(VERB_CODE);
        gTestResultModel.setDescription(DESCRIPTION);
        gTestResultModel.setCreated(CREATED);
        gTestResultModel.setResultCode(Constants.RESULT_STATUS_SUCCESS);

        gTestResultList.add(gTestResultModel);
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
     * Gets role list valid return model.
     */
    @Test
    public void getRoleList_Valid_ReturnModel() {
        // CONDITION
        when(rolesRepository.findByRoleSetCode(ROLE_SET_CODE)).thenReturn(gTestResultList);

        // TEST
        List<Roles> resultList = rolesService.getRoleList(ROLE_SET_CODE);

        // VERIFY
        Assertions.assertThat(resultList).isNotNull();
        assertEquals(gTestResultList, resultList);
        assertEquals(ROLE_SET_CODE, resultList.get(0).getRoleSetCode());
        assertEquals(RESOURCE_CODE, resultList.get(0).getResourceCode());
        assertEquals(VERB_CODE, resultList.get(0).getVerbCode());
        assertEquals(DESCRIPTION, resultList.get(0).getDescription());
        assertEquals(CREATED, resultList.get(0).getCreated());
    }


    /**
     * Gets role valid return model.
     */
    @Test
    public void getRole_Valid_ReturnModel() {
        // CONDITION
        when(rolesRepository.findByRoleSetCodeAndResourceCodeAndVerbCode(ROLE_SET_CODE, RESOURCE_CODE, VERB_CODE)).thenReturn(gTestResultModel);

        // TEST
        Roles resultModel = rolesService.getRole(ROLE_SET_CODE, RESOURCE_CODE, VERB_CODE);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
    }


    /**
     * Create role valid return model.
     */
    @Test
    public void createRole_Valid_ReturnModel() {
        // CONDITION
        when(rolesRepository.save(gTestModel)).thenReturn(gTestResultModel);

        // TEST
        Roles resultModel = rolesService.createRole(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
    }


    /**
     * Delete role valid return model.
     */
    @Test
    public void deleteRole_Valid_ReturnModel() {
        // SET
        gTestModel.setRoleSetCode(ROLE_SET_CODE);

        // CONDITION
        doNothing().when(rolesRepository).delete(gTestModel);
        when(commonService.setResultModel(Roles.class, Constants.RESULT_STATUS_SUCCESS)).thenReturn(gTestResultModel);

        // TEST
        Roles resultModel = rolesService.deleteRole(gTestModel);

        // VERIFY
        assertThat(resultModel).isNotNull();
        assertEquals(gTestResultModel, resultModel);
        assertEquals(ROLE_SET_CODE, resultModel.getRoleSetCode());
    }

}
