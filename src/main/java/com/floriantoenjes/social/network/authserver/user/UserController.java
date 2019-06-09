package com.floriantoenjes.social.network.authserver.user;

import com.floriantoenjes.social.network.authserver.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class UserController {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/sign-up")
    public Map<String, Object> signUp(@RequestBody User user) {
        Map<String, Object> partialUser = new HashMap<>();
        Set<Role> roles = new HashSet<>();

        roles.add(new Role("ROLE_USER"));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        partialUser.put("username", savedUser.getUsername());
        partialUser.put("roles", savedUser.getRoles());

        return partialUser;
    }

}
