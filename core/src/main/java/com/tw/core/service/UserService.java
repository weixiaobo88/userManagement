package com.tw.core.service;

import com.tw.core.User;
import com.tw.core.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by twer on 7/17/14.
 */
@Service
@Transactional(readOnly = true)
public class UserService {
//    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> listUser() {
        return userDAO.listUser();
    }

}
