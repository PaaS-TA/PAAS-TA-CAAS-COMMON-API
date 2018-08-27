package org.paasta.caas.common.api.adminToken;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * kuber와 관련 api를 호출 할 때 필요한 admin token을 저장하기 위한 model
 * token_name은 "caas_admin"으로 고정, property로 빼는 것이 좋은 것은 알지만
 * 릴리즈 수정을 줄이기 위함.
 * @author Hyerin
 * @since 2018.08.22
 * @version 20180822
 */
@Entity
@Table(name = "admin_token")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdminToken {

    @Id
    @Column(name = "token_name")
    private String tokenName;

    @Column(name = "token_value")
    @NotNull(message = "TOKEN VALUE cannot be null")
    @NotEmpty(message = "TOKEN VALUE is mandatory")
    private String tokenValue;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }
}
