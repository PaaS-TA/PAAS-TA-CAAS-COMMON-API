package org.paasta.caas.common.api.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-08-21
 */
@Data
public class RoleSet implements Serializable{
        private String roleSetCode;
        private String resourceCode;
        private String verbCode;
}
