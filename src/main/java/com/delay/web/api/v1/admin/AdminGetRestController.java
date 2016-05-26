package com.delay.web.api.v1.admin;

import com.delay.components.enums.Roles;
import com.delay.domain.entities.company.Company;
import com.delay.domain.entities.user.User;
import com.delay.services.BaseService;
import com.delay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.perishable.web.api.v1.admin;
 */
@RestController
@RequestMapping(value = "api/v1/admin")
@Secured({Roles.ROLE_ADMIN})
public class AdminGetRestController {

    @Autowired
    BaseService baseService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "company/all")
    public List<Company> all() {
        return baseService.getAll(Company.class);
    }

    @RequestMapping(value = "user/find/{username}")
    public List<User> likeByUsername(@PathVariable(value = "username") String username) {
        return userService.likeByUsername(username);
    }
}
