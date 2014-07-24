package com.tw.core.api;

import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by twer on 7/24/14.
 */
@Service
public class UsersService {
    private UsersDAO usersDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Transactional
    public List<User> findAll() {
        return usersDAO.findAll();
    }

    @Transactional
    public User findOne(long id) {
        return usersDAO.findOne(id);
    }

    @Transactional
    public void create(User user) {
        usersDAO.create(user);
    }

    @Transactional
    public void delete(long id) {
        usersDAO.delete(id);
    }

    public void update(User user) {
        usersDAO.update(user);
    }
}
