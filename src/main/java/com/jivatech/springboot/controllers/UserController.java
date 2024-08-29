package com.jivatech.springboot.controllers;

import com.jivatech.springboot.Entry.Entry;
import com.jivatech.springboot.Entry.User;
import com.jivatech.springboot.service.EntryService;
import com.jivatech.springboot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
       return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User usrDb   = userService.findByUserName(user.getUserName());
        if (usrDb != null) {
            usrDb.setUserName(user.getUserName());
            usrDb.setPassword(user.getPassword());
            userService.saveEntry(usrDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
