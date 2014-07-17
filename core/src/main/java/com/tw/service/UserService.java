package com.tw.service;

import com.tw.User;
import com.tw.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by twer on 7/17/14.
 */
@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public List<User> listUser() {
        return userDAO.listUser();
    }

}
