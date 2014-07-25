package com.tw.core;

import com.tw.core.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Created by twer on 7/24/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springTest.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class UsersDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void test() throws Exception {
        assertEquals(1,1);
    }


}
