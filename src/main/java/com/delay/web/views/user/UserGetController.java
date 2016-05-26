package com.delay.web.views.user;

import com.delay.components.enums.Roles;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.perishable.web.views.user;
 */
@Controller
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
public class UserGetController {

    @RequestMapping(value = "category")
    public String category() {
        return "user/category";
    }

    @RequestMapping(value = "subcategory")
    public String subcategory() {
        return "user/subcategory";
    }

    @RequestMapping(value = "product")
    public String product() {
        return "user/product";
    }

    @RequestMapping(value = "sales")
    public String sales() {
        return "user/sales";
    }

    @RequestMapping(value = "sales/add")
    public String salesAdd() {
        return "user/sales_add";
    }
}
