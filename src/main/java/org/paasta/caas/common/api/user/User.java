package org.paasta.caas.common.api.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.paasta.caas.common.api.common.Constants;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type User.
 */
@Entity
@Table(name = "user")
@Setter
@Getter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @Transient
    private String createdString;

    @Transient
    private String lastModifiedString;

    /**
     * Gets created string.
     *
     * @return the created string
     */
    public String getCreatedString() {
        return (created != null) ? created.format(DateTimeFormatter.ofPattern(Constants.STRING_DATE_TYPE)) : "";
    }

    /**
     * Gets last modified string.
     *
     * @return the last modified string
     */
    public String getLastModifiedString() {
        return (lastModified != null) ? lastModified.format(DateTimeFormatter.ofPattern(Constants.STRING_DATE_TYPE)) : "";
    }
}
