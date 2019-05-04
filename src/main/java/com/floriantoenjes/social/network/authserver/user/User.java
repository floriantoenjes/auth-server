package com.floriantoenjes.social.network.authserver.user;

import com.floriantoenjes.social.network.authserver.role.Role;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class User {

    private String id;

    private String username;

    private String password;

    private Set<Role> roles;

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
