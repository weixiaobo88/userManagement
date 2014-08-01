package com.tw.integration.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by twer on 7/30/14.
 */

@Repository
public class SpringFixture {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUser(Object... args) {
        jdbcTemplate.update("insert into USER ( ID, NAME, PASSWORD, EMAIL, AGE) values (?, ?, ?, ?, ?)", args);
    }

}

