package com.tw.integration;

import com.tw.core.User;
import com.tw.core.UsersDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertEquals;

/**
 * Created by twer on 7/28/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springIntegrationTest.xml")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class UsersDaoTest {

    @Autowired
    private UsersDAO usersDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        String cleanData= "delete from USER";
        jdbcTemplate.update(cleanData);

        jdbcTemplate.update("insert into USER ( ID, PASSWORD, NAME, EMAIL, AGE) values (?, ?, ?, ?, ?)", 1, "password", "Linne", "linne@abc.com", 24);
    }

    @Test
    public void when_findAll_should_return_correct_number_of_users() {
        int countOfUsers = jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);
        assertEquals(countOfUsers, usersDao.findAll().size());
    }

    @Test
    public void when_findOne_should_return_correct_user_info() {
        long id = 1;
        String name = jdbcTemplate.queryForObject("select name from USER where id = ?", new Object[]{id}, String.class);

        assertEquals(name, usersDao.findOne(id).getName());
    }

    @Test
    public void when_create_should_add_one_user() {
        User user = new User();
        String name = "Dom";
        String email = "dom@abc.com";
        user.setName(name);
        user.setEmail(email);
        user.setPassword("password");

        user.setAge(34);

        int countOfUsersBeforeCreate = jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);

        usersDao.create(user);

        int countOfUsersAfterCreate = jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);
        assertEquals(countOfUsersAfterCreate, countOfUsersBeforeCreate + 1);

        String queryEmail = jdbcTemplate.queryForObject("select email from USER where name = ?", new Object[]{name}, String.class);
        assertEquals(queryEmail, email);
    }

    @Test
    public void when_update_should_update_one_user() {
        User user = new User();
        String name = "Dom";
        user.setName(name);
        user.setEmail("dom@abc.com");
        user.setAge(34);
        user.setPassword("password");
        usersDao.create(user);

        int newAge = 40;
        user.setAge(40);
        usersDao.update(user);

        int queryAge = jdbcTemplate.queryForObject("select age from user where name = ?", new Object[]{name}, Integer.class);
        assertEquals(queryAge, newAge);
    }

    @Test
    public void when_delete_should_remove_one_user() {
        jdbcTemplate.update("insert into USER ( ID, NAME, PASSWORD, EMAIL, AGE) values (?, ?, ?, ?, ?)", 2, "Dom", "password", "dom@abc.com", 24);

        int countOfUsersBeforeDelete = jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);

        usersDao.delete(2);

        int countOfUsersAfterDelete = jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);

        assertEquals(countOfUsersAfterDelete, countOfUsersBeforeDelete - 1);
    }

    @Test
    public void when_deleteAll_should_remove_all_users_in_parameters() {
        jdbcTemplate.update("insert into USER ( ID, NAME, PASSWORD, EMAIL, AGE) values (?, ?, ?, ?, ?)", 2, "Dom", "password", "dom@abc.com", 24);
        jdbcTemplate.update("insert into USER ( ID, NAME, PASSWORD, EMAIL, AGE) values (?, ?, ?, ?, ?)", 3, "David", "password", "david@abc.com", 34);
        long[] ids = new long[2];
        ids[0] = 2;
        ids[1] = 3;

        int countOfUsersBeforeDeleteAll = jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);

        usersDao.deleteAll(ids);

        int countOfUsersAfterDeleteAll = jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);

        assertEquals(countOfUsersAfterDeleteAll, countOfUsersBeforeDeleteAll - ids.length);
    }

    @Test
    public void when_search_should_return_all_matched_users_on_email_or_name() {
        jdbcTemplate.update("insert into USER ( ID, NAME, PASSWORD, EMAIL, AGE) values (?, ?, ?, ?, ?)", 2, "Dom", "password", "dom@abc.com", 24);
        jdbcTemplate.update("insert into USER ( ID, NAME, PASSWORD, EMAIL, AGE) values (?, ?, ?, ?, ?)", 3, "David", "password", "david@abc.com", 34);

        String keyword = "abc";
        int countOfMatchedUser = jdbcTemplate.queryForObject("select count(*) from USER where name LIKE " + "'%" + keyword + "%'" + " or email LIKE " + "'%" + keyword + "%'", Integer.class);

        assertEquals(usersDao.search(keyword).size(), countOfMatchedUser);
    }

}
