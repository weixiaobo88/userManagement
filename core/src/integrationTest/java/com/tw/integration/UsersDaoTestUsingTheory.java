package com.tw.integration;

import com.tw.core.UsersDAO;
import com.tw.integration.fixture.SpringFixture;
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

    @Autowired
    private SpringFixture springFixture;

    @DataPoints
    public static String[] keywords = new String[] {"abc", "linne", "dom"};

    @Before
    public void setUp() throws Exception {
        new TestContextManager(getClass()).prepareTestInstance(this);
        jdbcTemplate.update("delete from USER");

        springFixture.insertUser(1, "Linne", "password", "linne@abc.com", 24);
        springFixture.insertUser(2, "Dom", "password", "dom@abc.com", 24);
        springFixture.insertUser(3, "David", "password", "david@abc.com", 34);
    }

    @Theory
    public void when_search_should_return_all_matched_users_on_email_or_name(final String keyword) {
        int countOfMatchedUser = jdbcTemplate.queryForObject("select count(*) from USER where name LIKE " + "'%" + keyword + "%'" + " or email LIKE " + "'%" + keyword + "%'", Integer.class);

        assertEquals(usersDao.search(keyword).size(), countOfMatchedUser);
    }

//    search result need to be tested
//    search result data should be separated
}
