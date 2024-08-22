package com.jivatech.springboot.controllers;

import com.jivatech.springboot.Entry.Entry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/entry")
public class EntryController {

    Map<Long, Entry> entry = new HashMap<>();

    @GetMapping
    public List<Entry> getAll() {
       return new ArrayList<>(entry.values());
    }

    @PostMapping
    public boolean cretaeEntry(@RequestBody Entry myentry) {
        entry.put(myentry.getId(), myentry);
        return true;
    }

    @GetMapping("id/{myId}")
    public Entry getListById(@PathVariable Long myId) {
        return entry.get(myId);
    }

    @DeleteMapping("id/{userId}")
    public Entry deleteEntryById(@PathVariable Long userId) {
        return entry.remove(userId);
    }

    @PutMapping("id/{id}")
    public Entry updateEntryList(@PathVariable Long id, @RequestBody Entry myEntry) {
        return  entry.put(id, myEntry);
    }
}
