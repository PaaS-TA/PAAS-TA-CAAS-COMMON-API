package org.paasta.caas.common.api.user;

import com.fasterxml.jackson.annotation.JsonAlias;
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
 * User Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.02
 */
@Entity
@Table(name = "user")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "USER ID cannot be null")
    @NotEmpty(message = "USER ID is mandatory")
    private String userId;

    @Column(name = "service_instance_id", nullable = false)
    @NotNull(message = "SERVICE INSTANCE ID cannot be null")
    @NotEmpty(message = "SERVICE INSTANCE ID is mandatory")
    private String serviceInstanceId;

    @Column(name = "caas_namespace", nullable = false)
    @NotNull(message = "NAMESPACE cannot be null")
    @NotEmpty(message = "NAMESPACE is mandatory")
    private String namespace;

    @Column(name = "caas_account_token_name", nullable = false)
    @NotNull(message = "CAAS ACCOUNT ACCESS TOKEN INSTANCE ID cannot be null")
    @NotEmpty(message = "CAAS ACCOUNT ACCESS TOKEN is mandatory")
    private String caasAccountAccessToken;

    // kubernetes 에서 service account 생성 name
    @Column(name = "caas_account_name", nullable = false)
    @NotNull(message = "CAAS ACCOUNT NAME cannot be null")
    @NotEmpty(message = " CAAS ACCOUNT NAME is mandatory")
    private String caasAccountName;

    @Column(name = "organization_guid", nullable = false)
    @NotNull(message = "ORGANIZATION GUID cannot be null")
    @NotEmpty(message = "ORGANIZATION GUID is mandatory")
    private String organizationGuid;

    @Column(name = "space_guid", nullable = false)
    @NotNull(message = "SPACE GUID cannot be null")
    @NotEmpty(message = "SPACE GUID is mandatory")
    private String spaceGuid;

    @Column(name = "role_set_code", nullable = false)
    @NotNull(message = "ROLE CODE cannot be null")
    @NotEmpty(message = "ROLE CODE is mandatory")
    private String roleSetCode;

    @Column(name = "description")
    private String description;

    @Column(name = "created", nullable = false, updatable = false)
    private String created;

    @Column(name = "last_modified", nullable = false)
    private String lastModified;

    @Transient
    private String resultCode;

    @Transient
    private String resultMessage;

    @PrePersist
    void preInsert() {
        if (this.created == null) {
            this.created = LocalDateTime.now(ZoneId.of(Constants.STRING_TIME_ZONE_ID)).format(DateTimeFormatter.ofPattern(Constants.STRING_DATE_TYPE));
        }

        if (this.lastModified == null) {
            this.lastModified = LocalDateTime.now(ZoneId.of(Constants.STRING_TIME_ZONE_ID)).format(DateTimeFormatter.ofPattern(Constants.STRING_DATE_TYPE));
        }
    }

    @PreUpdate
    void preUpdate() {
        if (this.lastModified == null) {
            this.lastModified = LocalDateTime.now(ZoneId.of(Constants.STRING_TIME_ZONE_ID)).format(DateTimeFormatter.ofPattern(Constants.STRING_DATE_TYPE));
        }
    }

    @JsonAlias({"namespace", "caasNamespace"})
    void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @JsonAlias({"caasAccountAccessToken", "caasAccountTokenName"})
    void setCaasAccountAccessToken(String caasAccountAccessToken) {
        this.caasAccountAccessToken = caasAccountAccessToken;
    }

}
