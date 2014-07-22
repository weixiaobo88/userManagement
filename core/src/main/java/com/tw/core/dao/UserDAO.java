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

//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    public List<User> listUser() {
        System.out.println("hhhhhhhhhhhhhhhhhhhhhh");
        System.out.println(sessionFactory);
        System.out.println(sessionFactory.getCurrentSession());
        System.out.println("kkkkkkkkkkkkkkkkkk");
        return sessionFactory.getCurrentSession().createQuery("from User")
                .list();
    }
}
