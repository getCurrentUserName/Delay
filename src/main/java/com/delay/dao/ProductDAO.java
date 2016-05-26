package com.delay.dao;

import com.delay.components.enums.ProductStatus;
import com.delay.domain.entities.category.Subcategory;
import com.delay.domain.entities.company.Company;
import com.delay.domain.entities.product.Product;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by LucidMinds on 19.05.16.
 * package com.perishable.dao;
 */
@Repository
@Transactional
public class ProductDAO extends BaseDAO {

    @SuppressWarnings("unchecked")
    public List<Product> getExpiring(Company company, Date date) {
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq(Product.COMPANY, company));
        criteria.add(Restrictions.eq(Product.STATUS, ProductStatus.ACTIVE.name()));
        criteria.add(Restrictions.between(Product.EXPIRE_DATE, new Date(), date));
        criteria.addOrder(Order.asc(Product.EXPIRE_DATE));
        criteria.setCacheable(true);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProducts(Company company, Subcategory subcategory) {
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq(Product.COMPANY, company));
        criteria.add(Restrictions.eq(Product.SUBCATEGORY, subcategory));
        criteria.add(Restrictions.eq(Product.STATUS, ProductStatus.ACTIVE.name()));
        criteria.addOrder(Order.asc(Product.ADDITION_DATE));
        criteria.setCacheable(true);
        return criteria.list();
    }
}
