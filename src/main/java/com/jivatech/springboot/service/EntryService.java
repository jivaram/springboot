package com.jivatech.springboot.service;

import com.jivatech.springboot.Entry.Entry;
import com.jivatech.springboot.Entry.User;
import com.jivatech.springboot.repository.EntryRepositroy;
import com.jivatech.springboot.repository.UserRepositroy;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class EntryService {

    @Autowired
    private EntryRepositroy entryRepositroy;

    @Autowired
    private UserService userService;

    public void saveEntry(Entry entry, String userName) {
        User user  = userService.findByUserName(userName);
        entry.setDate(LocalDateTime.now());
        Entry saved = entryRepositroy.save(entry);
        user.getEntry().add(saved);
        userService.saveEntry(user);
    }

    public void saveEntry(Entry entry) {
        entryRepositroy.save(entry);
    }

    public List<Entry> getAll() {
        return entryRepositroy.findAll();
    }

    public Optional<Entry> getEntryFromId(ObjectId myId) {
        return entryRepositroy.findById(myId);
    }

    public void deleteById(ObjectId myId, String userName) {
        User user = userService.findByUserName(userName);
        user.getEntry().removeIf(x -> x.getId().equals(myId));
        userService.saveEntry(user);
        entryRepositroy.deleteById(myId);
    }
}
