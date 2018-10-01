package org.paasta.caas.common.api.roles;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

/**
 * Role List Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-10-01
 */
@Data
public class RolesList {
    private String resultCode;

    @Column(name = "items")
    @ElementCollection(targetClass = String.class)
    private List<Roles> items;
}
