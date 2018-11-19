package org.paasta.caas.common.api.common;

/**
 * Constants 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.02
 */
public class Constants {

    public static final String RESULT_STATUS_SUCCESS = "SUCCESS";
    public static final String RESULT_STATUS_FAIL = "FAIL";
    public static final String STRING_DATE_TYPE = "yyyy-MM-dd HH:mm:ss";
    public static final String STRING_TIME_ZONE_ID = "Asia/Seoul";
    public static final String TARGET_CAAS_API = "caasApi";

    // API URI
    public static final String URI_API_ADMIN_TOKEN = "/adminToken";
    public static final String URI_API_ADMIN_TOKEN_DETAIL = "/adminToken/{tokenName:.+}";

    public static final String URI_API_ROLES = "/roles";
    public static final String URI_API_ROLES_ID_LIST = "/roles/{id:.+}";
    public static final String URI_API_ROLES_ID_DETAIL = "/roles/{id:.+}/detail";

    public static final String URI_API_USERS = "/users";
    public static final String URI_API_USERS_DETAIL = "/users/{id:.+}";
    public static final String URI_API_USERS_BY_SERVICE_INSTANCE_ID = "/users/serviceInstanceId/{serviceInstanceId:.+}";
    public static final String URI_API_USERS_BY_SUID_AND_ORG_GUID = "/users/serviceInstanceId/{serviceInstanceId:.+}/organizationGuid/{organizationGuid:.+}";
    public static final String URI_API_USERS_BY_SUID_AND_ORG_GUID_AND_USER_ID = "/users/serviceInstanceId/{serviceInstanceId:.+}/organizationGuid/{organizationGuid:.+}/userId/{userId:.+}/";

    public static final String URI_API_USERS_VALID_EXIST_NAMESPACE = "/users/userId/{userId}/namespace/{namespace}";

    private Constants() {
        throw new IllegalStateException();
    }
}
