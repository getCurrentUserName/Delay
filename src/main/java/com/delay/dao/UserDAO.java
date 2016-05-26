package com.delay.dao;

import com.delay.domain.entities.user.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class UserDAO extends BaseDAO {

    public User findById(UUID id) {
        return findById(User.class, id);
    }

    public User findByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq(User.USERNAME, username));
        criteria.setCacheable(true);
        return (User) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> likeByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.like(User.USERNAME, username, MatchMode.ANYWHERE));
        criteria.setCacheable(true);
        return criteria.list();
    }
}
