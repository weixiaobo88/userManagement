package com.tw.integration;

import com.tw.core.UsersDAO;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertEquals;

/**
 * Created by twer on 7/30/14.
 */
@RunWith(Theories.class)
@ContextConfiguration(locations = "classpath:springIntegrationTest.xml")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class UsersDaoTestUsingTheory {
    @Autowired
    private UsersDAO usersDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DataPoints
    public static String[] keywords = new String[] {"abc", "linne", "dom"};

    @Before
    public void setUp() throws Exception {
        new TestContextManager(getClass()).prepareTestInstance(this);
        String cleanData= "delete from USER";
        jdbcTemplate.update(cleanData);

        jdbcTemplate.update("insert into USER ( ID, NAME, EMAIL, AGE) values (?, ?, ?, ?)", 1, "Linne", "linne@abc.com", 24);
        jdbcTemplate.update("insert into USER ( ID, NAME, EMAIL, AGE) values (?, ?, ?, ?)", 2, "Dom", "dom@abc.com", 24);
        jdbcTemplate.update("insert into USER ( ID, NAME, EMAIL, AGE) values (?, ?, ?, ?)", 3, "David", "david@abc.com", 34);
    }


    @Theory
    public void when_search_should_return_all_matched_users_on_email_or_name(final String keyword) {
        int countOfMatchedUser = jdbcTemplate.queryForObject("select count(*) from USER where name LIKE " + "'%" + keyword + "%'" + " or email LIKE " + "'%" + keyword + "%'", Integer.class);

        assertEquals(usersDao.search(keyword).size(), countOfMatchedUser);
    }

//    search 的结果需要测试
//    serach 的结果应该也是data
}
