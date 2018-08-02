package org.paasta.caas.common.api.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.paasta.caas.common.api.common.Constants;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "desc")
    private String desc;

    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "last_modified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    /**
     * Gets created string.
     *
     * @return the created string
     */
    public String getCreatedString() {
        return new SimpleDateFormat(Constants.STRING_DATE_TYPE, Locale.KOREA).format(created);
    }

    /**
     * Gets last modified string.
     *
     * @return the last modified string
     */
    public String getLastModifiedString() {
        return new SimpleDateFormat(Constants.STRING_DATE_TYPE, Locale.KOREA).format(lastModified);
    }
}
