package com.jivatech.springboot.controllers;

import com.jivatech.springboot.Entry.Entry;
import com.jivatech.springboot.service.EntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/entry")
public class EntryControllerV2 {

    @Autowired
    private EntryService entryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
       List<Entry> entry = entryService.getAll();
        if (entry != null && !entry.isEmpty()) {
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Entry> cretaeEntry(@RequestBody Entry myentry) {
        try {
            myentry.setDate(LocalDateTime.now());
            entryService.saveEntry(myentry);
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

    @DeleteMapping("id/{userId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId userId) {
        entryService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Entry> updateEntryList(@PathVariable ObjectId id, @RequestBody Entry newEntry) {
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
