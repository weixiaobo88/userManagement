package com.tw.core.service;

import com.tw.core.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by twer on 7/31/14.
 */
@Service
public class PasswordService {

    private UsersDAO usersDAO;

    @Autowired
    public PasswordService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public PasswordService() {

    }

    public String encrypt(String password) {
        return encryptWithMd5(password);
    }

    private String encryptWithMd5(String password) {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, null);
    }

}
