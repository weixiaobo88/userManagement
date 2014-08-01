package com.tw.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by twer on 7/24/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springTest.xml")
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
public class UsersDAOTest {

    @Autowired
    private UsersDAO usersDAO;

    @Test
    public void test() throws Exception {
        assertEquals(1,1);
    }

    @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_users() {
        List<User> users = usersDAO.findAll();

        assertEquals(6, users.size());
        assertEquals("Tom", users.get(0).getName());
        assertEquals("tom@abc.com", users.get(0).getEmail());
        assertEquals(16, users.get(0).getAge());
    }

    @Transactional
    @Rollback
    @Test
    public void should_return_the_first_user() {
        User user = usersDAO.findOne(1);

        assertEquals("Tom", user.getName());
        assertEquals("tom@abc.com", user.getEmail());
        assertEquals(16, user.getAge());
    }

    @Transactional
    @Rollback
    @Test
    public void should_return_null_when_the_user_id_is_0() {
        User user = usersDAO.findOne(0);

        assertEquals(null, user);
    }

    @Transactional
    @Rollback
    @Test
    public void should_add_one_more_user() {
        User user = new User();
        user.setName("Linne");
        user.setEmail("linne@abc.com");
        user.setAge(24);

        assertEquals(6, usersDAO.findAll().size());

        usersDAO.create(user);

        assertEquals(7, usersDAO.findAll().size());
    }

    @Transactional
    @Rollback
    @Test
    public void should_update_user_age_from_16_to_26() {
        User user = usersDAO.findOne(1);

        assertEquals(16, user.getAge());

        user.setAge(26);
        usersDAO.update(user);

        assertEquals(26, user.getAge());
    }

    @Transactional
    @Rollback
    @Test
    public void should_delete_user_with_id_1() {
        usersDAO.delete(1);

        assertEquals(5, usersDAO.findAll().size());
    }

    @Transactional
    @Rollback
    @Test
    public void should_delete_all_users() {
        List<User> users = usersDAO.findAll();
        long[] ids = new long[6];
        for (int index = 0; index < users.size(); index++) {
            ids[index] = users.get(index).getId();
        }

        assertEquals(6, usersDAO.findAll().size());

        usersDAO.deleteAll(ids);

        assertEquals(0, usersDAO.findAll().size());
    }

    @Transactional
    @Rollback
    @Test
    public void should_get_correct_search_result() {
        String keyword = "rry";

        assertEquals(2, usersDAO.search(keyword).size());
    }

}
