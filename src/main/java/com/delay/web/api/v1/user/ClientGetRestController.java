package com.delay.web.api.v1.user;

import com.delay.components.enums.Roles;
import com.delay.domain.entities.category.Category;
import com.delay.domain.entities.category.Subcategory;
import com.delay.domain.entities.product.Product;
import com.delay.services.CategoryService;
import com.delay.services.ProductService;
import com.delay.services.user.UserDetailsServiceImpl;
import com.delay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.perishable.web.api.v1.admin;
 */
@RestController
@RequestMapping(value = "api/v1")
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
public class ClientGetRestController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "category/all")
    public List<Category> getCategories() {
        return categoryService.findByCompany(userDetailsService.getCurrentUser().getCompany());
    }

    @RequestMapping(value = "category/{id}/subcategories")
    public List<Subcategory> getSubcategories(@PathVariable(value = "id") UUID categoryId) {
        return productService.findById(Category.class, categoryId).getSubcategories();
    }

    @RequestMapping(value = "subcategory/{id}/products")
    public List<Product> getProducts(@PathVariable(value = "id") UUID subcategoryId) {
        Subcategory subcategory = productService.findById(Subcategory.class, subcategoryId);
        return productService.getProducts(subcategory);
    }

    @RequestMapping(value = "product/expiring", method = RequestMethod.GET)
    public List<Product> getExpiring() {
        return productService.getExpiring();
    }
}
