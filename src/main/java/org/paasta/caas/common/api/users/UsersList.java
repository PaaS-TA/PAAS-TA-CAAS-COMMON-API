package org.paasta.caas.common.api.users;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

@Data
public class UsersList {
    private String resultCode;

    @Column(name = "items")
    @ElementCollection(targetClass = String.class)
    private List<Users> items;
}
