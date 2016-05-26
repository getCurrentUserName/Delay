package com.delay.services;

import com.delay.components.enums.CommandStatus;
import com.delay.dao.BaseDAO;
import com.delay.domain.dto.CriteriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BaseService {

    @Autowired
    private BaseDAO baseDAO;

    public static final int max = 30;

    /**
     * Поиск по ID
     */
    public <T> T findById(Class<T> type, final UUID id) {
        return baseDAO.findById(type, id);
    }

    public <T> List<T> getAll(Class<T> type) {
        return baseDAO.getAll(type);
    }

    public CommandStatus persist(Object object) {
        return baseDAO.persist(object);
    }

    public UUID save(Object object) {
        return baseDAO.save(object);
    }

    public CommandStatus update(Object object) {
        return baseDAO.update(object);
    }

    public CommandStatus merge(Object object) {
        return baseDAO.merge(object);
    }

    public CommandStatus delete(Object object) {
        return baseDAO.delete(object);
    }


    public <T> List<T> findByCriteria(Class<T> type, String param, Object value) {
        return baseDAO.findByCriteria(type, param, value);
    }

    public <T> List<T> findByCriteria(Class<T> type, int first, CriteriaDTO... criteriaDTOs) {
        return baseDAO.findByCriteria(type, first, max, criteriaDTOs);
    }

    public <T> List<T> findByCriteria(Class<T> type, CriteriaDTO... criteriaDTOs) {
        return baseDAO.findByCriteria(type, criteriaDTOs);
    }
}
