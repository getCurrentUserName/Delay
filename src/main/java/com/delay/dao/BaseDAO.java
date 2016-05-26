package com.delay.dao;

import com.delay.components.enums.CommandStatus;
import com.delay.domain.dto.CriteriaDTO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
@Transactional
public class BaseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public <T> T findById(Class<T> type, final UUID id) {
        return sessionFactory.getCurrentSession().get(type, id);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> type) {
        return (List<T>) sessionFactory.getCurrentSession().createCriteria(type).list();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findByCriteria(Class<T> type, int first, int max, CriteriaDTO... criteriaDTOs) {
        Criteria criteria = getSession().createCriteria(type);
        for (CriteriaDTO criteriaDTO : criteriaDTOs) {
            criteria.add(Restrictions.eq(criteriaDTO.getParam(), criteriaDTO.getValue()));
        }
        criteria.setFirstResult(first);
        criteria.setMaxResults(max);
        criteria.setCacheable(true);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findByCriteria(Class<T> type, CriteriaDTO... criteriaDTOs) {
        Criteria criteria = getSession().createCriteria(type);
        for (CriteriaDTO criteriaDTO : criteriaDTOs) {
            criteria.add(Restrictions.eq(criteriaDTO.getParam(), criteriaDTO.getValue()));
        }
        criteria.setCacheable(true);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findByCriteria(Class<T> type,  String param, Object value) {
        Criteria criteria = getSession().createCriteria(type);
        criteria.add(Restrictions.eq(param, value));
        criteria.setCacheable(true);
        return criteria.list();
    }

    public CommandStatus persist(Object object) {
        try {
            getSession().persist(object);
            return CommandStatus.SUCCESS;
        } catch (Exception e) {
            new DAOException("Cannot persist", e, object).print();
            return CommandStatus.ERROR;
        }
    }

    public CommandStatus merge(Object object) {
        try {
            getSession().merge(object);
            return CommandStatus.SUCCESS;
        } catch (Exception e) {
            new DAOException("Cannot merge", e, object).print();
            return CommandStatus.ERROR;
        }
    }

    public CommandStatus update(Object object) {
        try {
            getSession().update(object);
            return CommandStatus.SUCCESS;
        } catch (Exception e) {
            new DAOException("Cannot update", e, object).print();
            return CommandStatus.ERROR;
        }
    }
    public CommandStatus delete(Object object) {
        try {
            getSession().delete(object);
            return CommandStatus.SUCCESS;
        } catch (Exception e) {
            new DAOException("Cannot delete", e, object).print();
            return CommandStatus.ERROR;
        }
    }

    public UUID save(Object object) {
        return (UUID) getSession().save(object);
    }

}