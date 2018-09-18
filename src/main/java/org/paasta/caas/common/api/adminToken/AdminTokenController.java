package org.paasta.caas.common.api.adminToken;

import org.springframework.web.bind.annotation.*;

/**
 * AdminToken Controller 클래스
 *
 * @author dojjang
 * @version 1.0
 * @since 2018.08.27
 */
@RestController
@RequestMapping("/adminToken")
public class AdminTokenController {

    private final AdminTokenService adminTokenService;

    /**
     * Instantiates a new Admin token controller.
     *
     * @param adminTokenService the admin token service
     */
    public AdminTokenController(AdminTokenService adminTokenService) {
        this.adminTokenService = adminTokenService;
    }


    /**
     * Admin token 상세 정보를 조회한다.
     *
     * @param token_name the token_name
     * @return the AdminToken
     */
    @GetMapping(value = "/{token_name:.+}")
    AdminToken getTokenValue(@PathVariable("token_name") String token_name) {
        return adminTokenService.getTokenValue(token_name);
    }


    /**
     * Admin token 정보를 등록한다.
     *
     * @param adminToken the admin token
     * @return the admin token
     */
    @PostMapping
    AdminToken createAdminToken(@RequestBody AdminToken adminToken) {
        return adminTokenService.createAdminToken(adminToken);
    }

}
