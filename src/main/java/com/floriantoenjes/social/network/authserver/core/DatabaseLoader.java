package com.floriantoenjes.social.network.authserver.core;

import com.floriantoenjes.social.network.authserver.role.Role;
import com.floriantoenjes.social.network.authserver.role.RoleRepository;
import com.floriantoenjes.social.network.authserver.user.User;
import com.floriantoenjes.social.network.authserver.user.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

//@Component
public class DatabaseLoader implements ApplicationRunner {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    public DatabaseLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole).subscribe();

        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole).subscribe();

        Set<Role> adminRoles = new HashSet<Role>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);

        User admin = new User("admin", "password", adminRoles);
        userRepository.save(admin).subscribe();
    }
}
