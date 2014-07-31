package com.tw.core;

import com.tw.core.service.PasswordService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by twer on 7/30/14.
 */

public class PasswordServiceTest {

    @Test
    public void when_encode_password_with_md5_should_return_encrypted_password() {
        PasswordService passwordService = new PasswordService();
        String password = "hello";
        String hashedPassword = passwordService.encrypt(password);

        assertEquals("5d41402abc4b2a76b9719d911017c592", hashedPassword);
    }

}
