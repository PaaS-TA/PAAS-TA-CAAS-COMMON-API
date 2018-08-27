package org.paasta.caas.common.api.adminToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminTokenService {

    private final AdminTokenRepository adminTokenRepository;

    /**
     * Instantiates a new User service.
     *
     * @param adminTokenRepository the adminToken repository
     */
    @Autowired
    public AdminTokenService(AdminTokenRepository adminTokenRepository) {
        this.adminTokenRepository = adminTokenRepository;
    }

    /**
     * Gets token_value.
     *
     * @param token_name the token_name
     * @return the AdminToken
     */
    AdminToken getTokenValue(String token_name) {
        return adminTokenRepository.getOne(token_name);
    }
}
