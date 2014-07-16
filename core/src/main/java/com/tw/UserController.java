package com.tw;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by twer on 7/16/14.
 */
@RestController
@RequestMapping("/service/greeting")
public class UserController {
//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
//
//    @RequestMapping("/list")
//    public User user(@RequestParam(value="name", required=false, defaultValue="World") String name) {
//        return new User(counter.incrementAndGet(),
//                String.format(template, name));
//    }
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getGreeting(@PathVariable String name) {
        String result="Hello "+name;
        return result;
    }

}
