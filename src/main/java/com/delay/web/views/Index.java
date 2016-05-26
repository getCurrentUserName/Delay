package com.delay.web.views;

import com.delay.components.enums.Roles;
import com.delay.services.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by LucidMinds on 07.05.16.
 * package com.boltalka.web.views;
 */
@Controller
public class Index {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/")
    public String index(Principal principal) {
        if (principal == null) {
            return "redirect:login";
        }
        String role = userDetailsService.getAuthUserRole();
        if (role.equals(Roles.ROLE_ADMIN)) {
            return "redirect:admin";
        }
        return "user/index";
    }

    @RequestMapping("help")
    public String help() {
        return "help";
    }
}
