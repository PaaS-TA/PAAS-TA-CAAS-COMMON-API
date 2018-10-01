package org.paasta.caas.common.api.roles;

import lombok.Data;

import java.io.Serializable;

/**
 * Roles PK 설정을 위한 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-27
 */
@Data
public class RoleSets implements Serializable{
    private String roleSetCode;
    private String resourceCode;
    private String verbCode;
}
