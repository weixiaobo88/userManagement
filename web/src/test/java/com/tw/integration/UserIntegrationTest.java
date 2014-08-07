package com.tw.integration;

import com.tw.core.User;
import com.tw.core.UsersService;
import com.tw.core.service.PasswordService;
import com.tw.web.api.UsersController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by twer on 7/24/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:test-spring.xml"})
public class UserIntegrationTest {

    @Mock
    private UsersService usersService;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    UsersController usersController = new UsersController(usersService, passwordService);

    private MockMvc mockMvc;

    List<User> users = new ArrayList<User>();


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();

        User user1 = new User();
        user1.setId(0);
        user1.setName("user1");
        users.add(0, user1);
    }

    @Test
    public void when_visit_api_v1_users_should_return_ok_and_correct_users() throws Exception {
        when(usersService.findAll()).thenReturn(users);

        this.mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(users.get(0).getName()));


        verify(usersService, times(1)).findAll();
        verifyNoMoreInteractions(usersService);
    }

    @Test
    public void when_visit_api_v1_users_id_should_return_ok_and_correct_user() throws Exception {
        long id = 0;
        when(usersService.findOne(id)).thenReturn(users.get(0));

        this.mockMvc.perform(get("/api/v1/users/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(users.get(0).getName()));

        verify(usersService, times(1)).findOne(id);
        verifyNoMoreInteractions(usersService);
    }

    @Test
    public void when_create_user_on_api_v1_users_should_return_created_and_redirect_to_correct_url() throws Exception {
        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"user\", \"email\": \"user@abc.com\", \"password\": \"password\", \"age\": 12}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.endsWith("/1")));
    }

}
