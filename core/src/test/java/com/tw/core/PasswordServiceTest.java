package com.tw.core;

import com.tw.core.service.PasswordService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by twer on 7/31/14.
 */
public class PasswordServiceTest {
    @Test
    public void when_encrypt_should_return_encrypted_string() {
        String password = "hello";
        String encrptedPassword = "5d41402abc4b2a76b9719d911017c592";

        assertEquals(encrptedPassword, new PasswordService().encrypt(password));
    }
}
