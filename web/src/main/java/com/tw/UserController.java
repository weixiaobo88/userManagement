package com.tw;

import com.tw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by twer on 7/16/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public User user(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new User(counter.incrementAndGet(),
                String.format(template, name));
    }
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getGreeting(@PathVariable String name) {
        String result="Hello "+name;
        return result;
    }

    @RequestMapping("/index")
    public List<User> listUser() {
        return userService.listUser();
    }

}
