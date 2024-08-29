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
import java.util.*;

@RestController
@RequestMapping("/entry")
public class EntryControllerV2 {

    @Autowired
    private EntryService entryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllEntryOfUser(@PathVariable String userName) {
       User user = userService.findByUserName(userName);
       List<Entry> entry = user.getEntry();
        if (entry != null && !entry.isEmpty()) {
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<Entry> cretaeUserEntry(@RequestBody Entry myentry, @PathVariable String userName) {
        try {
            entryService.saveEntry(myentry, userName);
            return  new ResponseEntity<>(myentry, HttpStatus.CREATED);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<Entry> getListById(@PathVariable ObjectId myId) {
        Optional<Entry> entry = entryService.getEntryFromId(myId);
        if (entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{userId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId userId, @PathVariable String userName) {
        entryService.deleteById(userId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{id}")
    public ResponseEntity<Entry> updateEntryList(@PathVariable ObjectId id, @RequestBody Entry newEntry, @PathVariable String userName) {
        Entry oldentry = entryService.getEntryFromId(id).orElse(null);
        if (oldentry != null) {
            oldentry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): oldentry.getTitle());
            oldentry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): oldentry.getContent());
            entryService.saveEntry(oldentry);
            return  new ResponseEntity<>(oldentry, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
