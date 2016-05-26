package com.delay.services;

import com.delay.dao.ProductDAO;
import com.delay.domain.entities.category.Subcategory;
import com.delay.domain.entities.company.Company;
import com.delay.domain.entities.product.Product;
import com.delay.domain.entities.user.User;
import com.delay.services.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by LucidMinds on 19.05.16.
 * package com.perishable.services;
 */
@Service
public class ProductService extends ProductDAO {

    @Autowired
    ProductDAO productDAO;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public List<Product> getExpiring() {
        return getExpiring(5);
    }

    public List<Product> getExpiring(int date) {
        User user = userDetailsService.getCurrentUser();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, date);
        Date expiryDate = calendar.getTime();
        return productDAO.getExpiring(user.getCompany(), expiryDate);
    }

    public List<Product> getProducts(Subcategory subcategory) {
        User user = userDetailsService.getCurrentUser();
        return getProducts(user.getCompany(), subcategory);
    }

    public List<Product> getProducts(Company company, Subcategory subcategory) {
        return productDAO.getProducts(company, subcategory);
    }
}
