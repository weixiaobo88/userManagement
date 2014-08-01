package com.tw.core;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by twer on 7/24/14.
 */
@Repository
@Transactional
public class UsersDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UsersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User")
                .list();
    }

    public User findOne(long id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void delete(long id) {
        User user = findOne(id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void deleteAll(long[] idList) {
        for (long id : idList) {
            delete(id);
        }
    }

    public List<User> search(String keyword) {
        String query = "FROM User WHERE name LIKE :keyword or email LIKE :keyword";

        return sessionFactory.getCurrentSession().createQuery(query)
                .setString("keyword", "%" + keyword + "%")
                .list();
    }
}