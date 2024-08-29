package com.jivatech.springboot.service;

import com.jivatech.springboot.Entry.Entry;
import com.jivatech.springboot.Entry.User;
import com.jivatech.springboot.repository.EntryRepositroy;
import com.jivatech.springboot.repository.UserRepositroy;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepositroy userRepositroy;

    public void saveEntry(User user) {
        userRepositroy.save(user);
    }

    public List<User> getAll() {
        return userRepositroy.findAll();
    }

    public Optional<User> getEntryFromId(ObjectId myId) {
        return userRepositroy.findById(myId);
    }

    public void deleteById(ObjectId myId) {
        userRepositroy.deleteById(myId);
    }

    public User findByUserName(String userName) {
        return userRepositroy.findByUserName(userName);
    }
}
