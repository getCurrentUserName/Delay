package com.delay.services;

import com.delay.domain.dto.CriteriaDTO;
import com.delay.domain.entities.category.Category;
import com.delay.domain.entities.company.Company;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.perishable.services;
 */
@Service
public class CategoryService extends BaseService {

    public List<Category> findByCompany(Company company) {
        return findByCriteria(Category.class, new CriteriaDTO(Category.COMPANY, company));
    }

}
