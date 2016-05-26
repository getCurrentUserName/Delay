package com.delay.services.user;

import com.delay.components.enums.Roles;
import com.delay.dao.UserDAO;
import com.delay.components.enums.CommandStatus;
import com.delay.domain.entities.user.User;
import com.delay.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Поиск по id
     * @param id - id пользователя
     * */
    public User findById(UUID id) {
        return userDAO.findById(id);
    }

    /**
     * Поиск по логину
     * @param username - логин пользователя
     * */
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public List<User> likeByUsername(String username) {
        return userDAO.likeByUsername(username);
    }

    public CommandStatus register(User registerUser) {
        User user = findByUsername(registerUser.getUsername());
        registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        registerUser.setRole(Roles.ROLE_USER);
        if (user != null) {
            return CommandStatus.USERNAME_EXIST;
        }
        return persist(registerUser);
    }
}