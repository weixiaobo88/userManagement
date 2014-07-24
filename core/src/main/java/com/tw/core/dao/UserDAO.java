package com.tw.core.dao;

import com.tw.core.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by twer on 7/17/14.
 */
@Repository
@Transactional(readOnly = true)
public class UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> listUser() {
        return sessionFactory.getCurrentSession().createQuery("from User")
                .list();
    }

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User findUserById(long id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void deleteUser(long id) {
        User user = findUserById(id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    public void deleteUserList(long[] ids) {
        for (int index = 0; index < ids.length; index++) {
            deleteUser(ids[index]);
        }
    }
}
