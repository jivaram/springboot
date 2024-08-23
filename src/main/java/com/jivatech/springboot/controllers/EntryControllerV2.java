package com.jivatech.springboot.controllers;

import com.jivatech.springboot.Entry.Entry;
import com.jivatech.springboot.service.EntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/entry")
public class EntryControllerV2 {

    @Autowired
    private EntryService entryService;

    @GetMapping
    public List<Entry> getAll() {
        return  entryService.getAll();
    }

    @PostMapping
    public Entry cretaeEntry(@RequestBody Entry myentry) {
        myentry.setDate(LocalDateTime.now());
        entryService.saveEntry(myentry);
        return  myentry;
    }

    @GetMapping("id/{myId}")
    public Entry getListById(@PathVariable ObjectId myId) {
        return  entryService.getEntryFromId(myId).orElse(null);
    }

    @DeleteMapping("id/{userId}")
    public boolean deleteEntryById(@PathVariable ObjectId userId) {
        entryService.deleteById(userId);
        return true;
    }

    @PutMapping("id/{id}")
    public Entry updateEntryList(@PathVariable ObjectId id, @RequestBody Entry newEntry) {
        Entry oldentry = entryService.getEntryFromId(id).orElse(null);
        if (oldentry != null) {
            oldentry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): oldentry.getTitle());
            oldentry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): oldentry.getContent());
        }
        entryService.saveEntry(oldentry);
        return  oldentry;
    }
}
