package com.delay.web.api.v1.user;

import com.delay.components.enums.ProductStatus;
import com.delay.components.enums.Roles;
import com.delay.domain.dto.SalesDTO;
import com.delay.domain.dto.ResponseResult;
import com.delay.domain.entities.category.Category;
import com.delay.domain.entities.category.Subcategory;
import com.delay.domain.entities.product.Product;
import com.delay.domain.entities.user.User;
import com.delay.services.ProductService;
import com.delay.services.SalesService;
import com.delay.services.user.UserDetailsServiceImpl;
import com.delay.services.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.delay.web.api.v1;
 */
@RestController
@RequestMapping(value = "api/v1")
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
public class ClientPostRestController {

    @Autowired
    ProductService productService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    DateUtils dateUtils;
    @Autowired
    SalesService salesService;

    /**
     *
     * */
    @RequestMapping(value = "category/update")
    public ResponseResult updateCategory(@RequestParam(value = "id") UUID categoryId,
                                         @RequestParam(value = "action", required = false) String action,
                                         @RequestParam(value = "name", required = false) String name) {
        Category category = productService.findById(Category.class, categoryId);
        category.setName(name);
        if (action.equals("delete")) {
            return new ResponseResult(productService.delete(category));
        }
        return new ResponseResult(productService.update(category));
    }

    /**
     *
     * */
    @RequestMapping(value = "category/add")
    public ResponseResult addCategory(@RequestParam(value = "name") String name) {
        Category category = new Category();
        User user = userDetailsService.getCurrentUser();
        category.setName(name);
        category.setCompany(user.getCompany());
        return new ResponseResult(productService.persist(category));
    }

    /**
     *
     * */
    @RequestMapping(value = "subcategory/update")
    public ResponseResult updateSubcategory(@RequestParam(value = "id") UUID subcategoryId,
                                            @RequestParam(value = "action", required = false) String action,
                                            @RequestParam(value = "name", required = false) String name) {
        Subcategory subcategory = productService.findById(Subcategory.class, subcategoryId);
        subcategory.setName(name);
        if (action.equals("delete")) {
            return new ResponseResult(productService.delete(subcategory));
        }
        return new ResponseResult(productService.update(subcategory));
    }

    /**
     *
     * */
    @RequestMapping(value = "subcategory/add")
    public ResponseResult addSubcategory(@RequestParam(value = "name") String name,
                                            @RequestParam(value = "categoryId") UUID categoryId) {
        Category category = productService.findById(Category.class, categoryId);
        Subcategory subcategory = new Subcategory();
        subcategory.setName(name);
        subcategory.setCategory(category);
        return new ResponseResult(productService.persist(subcategory));
    }

    /**
     *
     * */
    @RequestMapping(value = "product/update")
    public ResponseResult updateProduct(@RequestParam(value = "id") UUID subcategoryId,
                                        @RequestParam(value = "count", required = false, defaultValue = "-9999") long count,
                                        @RequestParam(value = "price", required = false, defaultValue = "-9999") double price,
                                        @RequestParam(value = "country", required = false) String country,
                                        @RequestParam(value = "expiry", required = false) String expiry,
                                        @RequestParam(value = "action", required = false) String action,
                                        @RequestParam(value = "name", required = false) String name) throws ParseException {
        Product product = productService.findById(Product.class, subcategoryId);
        if (name != null) {
            product.setName(name);
        }
        if (country != null) {
            product.setCountry(country);
        }
        if (count != -9999) {
            product.setCount(count);
        }
        if (price != -9999) {
            product.setPrice(price);
        }
        if (expiry != null) {
            product.setExpireDate(dateUtils.toDate(expiry));
        }
        if (action.equals("delete")) {
            product.setStatus(ProductStatus.DELETED.name());
            product.setRemovalDate(new Date());
            return new ResponseResult(productService.update(product));
        }
        if (action.equals("restore")) {
            product.setStatus(ProductStatus.ACTIVE.name());
            product.setRemovalDate(null);
            return new ResponseResult(productService.update(product));
        }
        return new ResponseResult(productService.update(product));
    }

    /**
     *
     * */
    @RequestMapping(value = "product/add")
    public ResponseResult addProduct(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "count") long count,
                                     @RequestParam(value = "country", required = false) String country,
                                     @RequestParam(value = "expiry") int expiry,
                                     @RequestParam(value = "price", required = false, defaultValue = "-9999") double price,
                                     @RequestParam(value = "subcategoryId") UUID subcategoryId) {
        User user = userDetailsService.getCurrentUser();
        Subcategory subcategory = productService.findById(Subcategory.class, subcategoryId);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, expiry);
        Date expiryDate = calendar.getTime();

        Product product = new Product();
        product.setStatus(ProductStatus.ACTIVE.name());
        product.setName(name);
        product.setCompany(user.getCompany());
        product.setSubcategory(subcategory);
        product.setCount(count);
        product.setCountry(country);
        if (price != -9999) {
            product.setPrice(price);
        } else {
            product.setPrice(0);
        }
        product.setAdditionDate(new Date());
        product.setExpireDate(expiryDate);
        return new ResponseResult(productService.persist(product));
    }


    @RequestMapping(value = "product/expiring", method = RequestMethod.POST)
    public List<Product> getExpiring(@RequestParam(value = "date") int date) {
        return productService.getExpiring(date);
    }

    @RequestMapping(value = "sales/add", method = RequestMethod.POST)
    public ResponseResult salesAdd(@RequestBody SalesDTO[] sales) {
        return new ResponseResult(salesService.addSales(sales));
    }
}
