package com.tw.web.api;

import com.tw.core.User;
import com.tw.core.UsersService;
import com.tw.core.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by twer on 7/24/14.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private UsersService usersService;
    private PasswordService passwordService;

    @Autowired
    public UsersController(UsersService usersService, PasswordService passwordService) {
        this.usersService = usersService;
        this.passwordService = passwordService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return usersService.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> findOne(@PathVariable("userId") long id) {
        User user = usersService.findOne(id);

        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        usersService.create(user);
        passwordService.encryptPassword(user);
        response.setHeader("Location", request.getRequestURL().append("/").append(user.getId()).toString());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("userId") long id, @RequestBody User user) {
        usersService.update(user);
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") long id) {
        usersService.delete(id);
    }

    @RequestMapping(value = "/:batch", method = RequestMethod.DELETE )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@RequestBody long[] ids) {
        usersService.deleteAll(ids);
    }

    @RequestMapping(value = "/:search", method = RequestMethod.GET)
    public List<User> search( @RequestParam(value = "keyword") String keyword) {
        return usersService.search(keyword);
    }

}
