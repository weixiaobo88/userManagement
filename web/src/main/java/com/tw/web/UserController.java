package com.tw.web;

import com.tw.core.User;
import com.tw.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by twer on 7/16/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/all")
    public List<User> listUser() {
        return userService.listUser();
    }

    @RequestMapping(value = "/")
    public ModelAndView listOfUsers() {
        ModelAndView modelAndView = new ModelAndView("userList");
        List<User> users = userService.listUser();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUserPage() {
        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("user", new User());
        return  modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("userList");
        userService.addUser(user);
        String message = "User was successfully added.";
        modelAndView.addObject("message", message);
        modelAndView.addObject("users", userService.listUser());
        return  modelAndView;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("userEdit");
        User user = userService.findUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, @PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("userList");
        userService.updateUser(user);
        String message = "User was successfully edited.";
        modelAndView.addObject("message", message);
        modelAndView.addObject("users", userService.listUser());
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("userList");
        userService.deleteUser(id);
        String message = "User was successfully deleted.";
        modelAndView.addObject("message", message);
        modelAndView.addObject("users", userService.listUser());
        return modelAndView;
    }

    @RequestMapping(value = "/deleteAll/{ids:(?:[0-9]+[,]?)+}", method = RequestMethod.GET)
    public ModelAndView batchDeleteUser(@PathVariable long[] ids) {
        System.out.println("---------------");
        ModelAndView modelAndView = new ModelAndView("userList");
        System.out.println(ids.length);
        System.out.println("---------------");
        userService.deleteUserList(ids);
        modelAndView.addObject("users", userService.listUser());
        return  modelAndView;
    }
}
