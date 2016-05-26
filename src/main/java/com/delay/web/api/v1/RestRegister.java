package com.delay.web.api.v1;

import com.delay.domain.dto.ResponseResult;
import com.delay.components.enums.CommandStatus;
import com.delay.components.enums.Roles;
import com.delay.domain.entities.user.User;
import com.delay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/register")
@Secured({Roles.ROLE_ANONYMOUS})
public class RestRegister {

    @Autowired
    UserService userService;
    /**
     * Регистрация
     * @param username - логин
     * @param password - пароль
     * */
    @RequestMapping(method = RequestMethod.POST)
    @Secured({Roles.ROLE_ANONYMOUS})
    public ResponseResult register(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Roles.ROLE_USER);
        CommandStatus commandStatus = userService.register(user);
        return new ResponseResult(commandStatus);
    }
}
