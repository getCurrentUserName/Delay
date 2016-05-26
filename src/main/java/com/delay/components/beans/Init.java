package com.delay.components.beans;

import com.delay.components.enums.Roles;
import com.delay.domain.entities.user.User;
import com.delay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by LucidMinds on 19.05.16.
 * package com.perishable.components.beans;
 */
@Service
@PropertySource(value = {"classpath:users.properties"})
public class Init {

    @Autowired
    private Environment env;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @PostConstruct
    public void add() {
        if (userService.findByUsername(env.getProperty(USERNAME)) ==null) {
            User user = new User();
            user.setUsername(env.getProperty(USERNAME));
            user.setPassword(passwordEncoder.encode(env.getProperty(PASSWORD)));
            user.setRole(Roles.ROLE_ADMIN);
            userService.persist(user);
        }
    }
}
