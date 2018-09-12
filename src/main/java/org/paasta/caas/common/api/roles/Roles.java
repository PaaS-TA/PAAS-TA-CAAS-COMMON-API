package org.paasta.caas.common.api.roles;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.paasta.caas.common.api.common.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Roles Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-27
 */
@Entity
@Table(name = "caas_user_role_set")
@IdClass(RoleSets.class)
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Roles {
    @Id
    @Column(name = "role_set_code", nullable = false)
    @NotNull(message = "role_set_code cannot be null")
    @NotEmpty(message = "role_set_code is mandatory")
    private String roleSetCode;

    @Id
    @Column(name = "resource_code", nullable = false)
    @NotNull(message = "resource_code cannot be null")
    @NotEmpty(message = "resource_code is mandatory")
    private String resourceCode;

    @Id
    @Column(name = "verb_code", nullable = false)
    @NotNull(message = "verb_code cannot be null")
    @NotEmpty(message = "verb_code is mandatory")
    private String verbCode;

    @Column(name = "description")
    private String description;

    @Column(name = "created", nullable = false, updatable = false)
    private String created;

    @Transient
    private String resultCode;

    @PrePersist
    void preInsert() {
        if (this.created == null) {
            this.created = LocalDateTime.now(ZoneId.of(Constants.STRING_TIME_ZONE_ID)).format(DateTimeFormatter.ofPattern(Constants.STRING_DATE_TYPE));
        }
    }
}
