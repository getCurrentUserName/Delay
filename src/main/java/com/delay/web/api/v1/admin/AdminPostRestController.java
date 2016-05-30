package com.delay.web.api.v1.admin;

import com.delay.components.enums.Roles;
import com.delay.domain.dto.ResponseResult;
import com.delay.domain.entities.company.Company;
import com.delay.domain.entities.user.User;
import com.delay.services.BaseService;
import com.delay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.delay.web.api.v1;
 */
@RestController
@RequestMapping(value = "api/v1/admin")
@Secured({Roles.ROLE_ADMIN})
public class AdminPostRestController {

    @Autowired
    BaseService baseService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "company/update")
    public ResponseResult updateCompany(@RequestParam(value = "id") UUID companyId,
                                        @RequestParam(value = "name") String name) {
        Company company = baseService.findById(Company.class, companyId);
        company.setName(name);
        return new ResponseResult(baseService.update(company));
    }

    @RequestMapping(value = "company/add")
    public ResponseResult updateCompany(@RequestParam(value = "name") String name,
                                        @RequestParam(value = "username") String username,
                                        @RequestParam(value = "password") String password) {
        Company company = new Company();
        User user = new User();
        company.setName(name);
        UUID companyId = baseService.save(company);
        company.setId(companyId);
        user.setUsername(username);
        user.setPassword(password);
        user.setCompany(company);
        return new ResponseResult(userService.register(user));
    }
}
