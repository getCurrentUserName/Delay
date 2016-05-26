package com.delay.services;

import com.delay.components.enums.CommandStatus;
import com.delay.domain.dto.SalesDTO;
import com.delay.domain.entities.company.Company;
import com.delay.domain.entities.product.Product;
import com.delay.domain.entities.product.Sales;
import com.delay.domain.entities.user.User;
import com.delay.services.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by LucidMinds on 20.05.16.
 * package com.perishable.services;
 */
@Service
public class SalesService extends BaseService {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public CommandStatus addSales(SalesDTO[] salesDTOs) {
        for (SalesDTO salesDTO : salesDTOs) {
            addSales(salesDTO.getProductId(), salesDTO.getCount());
        }
        return CommandStatus.SUCCESS;
    }

    public CommandStatus addSales(UUID productId, long count) {
        Product product = findById(Product.class, productId);
        User user = userDetailsService.getCurrentUser();
        Company company = user.getCompany();
        Sales sales = new Sales();
        sales.setCount(count);
        sales.setCompany(company);
        sales.setProduct(product);
        sales.setProductName(product.getName());
        sales.setSaleDate(new Date());
        CommandStatus commandStatus = persist(sales);
        if (commandStatus == CommandStatus.SUCCESS) {
            product.setCount(product.getCount() - count);
        }
        return update(product);
    }
}
