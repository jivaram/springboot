package com.jivatech.springboot.repository;

import com.jivatech.springboot.Entry.Entry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EntryRepositroy extends MongoRepository<Entry, ObjectId> {

}
