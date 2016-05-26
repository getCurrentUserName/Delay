package com.delay.web.views.admin;

import com.delay.components.enums.CommandStatus;
import com.delay.components.enums.Roles;
import com.delay.domain.entities.company.Company;
import com.delay.domain.entities.user.User;
import com.delay.services.BaseService;
import com.delay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.perishable.web.views.admin;
 */
@Controller
@RequestMapping(value = "admin")
@Secured({Roles.ROLE_ADMIN})
public class AdminIndex {

    @Autowired
    BaseService baseService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "")
    public String adminPanel(ModelMap model) {
        //model.addAttribute("companyList", baseService.getAll(Company.class));
        return "admin/admin_index";
    }

    @RequestMapping(value = "company/add", method = RequestMethod.POST)
    public String adminPanel(ModelMap model,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password) {
        Company company = new Company();
        User user = new User();
        if (userService.findByUsername(username) != null) {
            model.addAttribute("result", CommandStatus.USERNAME_EXIST);
            return "admin/admin_index";
        }
        company.setName(name);
        UUID companyId = baseService.save(company);
        company.setId(companyId);
        user.setCompany(company);
        user.setUsername(username);
        user.setPassword(password);
        model.addAttribute("result", userService.register(user));
        return "admin/admin_index";
    }
}
