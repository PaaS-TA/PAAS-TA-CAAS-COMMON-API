package org.paasta.caas.common.api.adminToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminToken")
public class AdminTokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminTokenController.class);

    private final AdminTokenService adminTokenService;

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
}
