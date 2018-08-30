package org.paasta.caas.common.api.adminToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminTokenController.class);

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
     * Gets token_value.
     *
     * @param token_name the token_name
     * @return the AdminToken
     */
    @GetMapping(value = "/{token_name:.+}")
    AdminToken getTokenValue(@PathVariable("token_name") String token_name) {
        return adminTokenService.getTokenValue(token_name);
    }


    /**
     * Create admin token admin token.
     *
     * @param adminToken the admin token
     * @return the admin token
     */
    @PostMapping
    AdminToken createAdminToken(@RequestBody AdminToken adminToken) {
        return adminTokenService.createAdminToken(adminToken);
    }
}
