package com.jivatech.springboot.service;

import com.jivatech.springboot.Entry.Entry;
import com.jivatech.springboot.repository.EntryRepositroy;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EntryService {

    @Autowired
    private EntryRepositroy entryRepositroy;

    public void saveEntry(Entry entry) {
        entryRepositroy.save(entry);
    }

    public List<Entry> getAll() {
        return entryRepositroy.findAll();
    }

    public Optional<Entry> getEntryFromId(ObjectId myId) {
        return entryRepositroy.findById(myId);
    }

    public void deleteById(ObjectId myId) {
        entryRepositroy.deleteById(myId);
    }
}
