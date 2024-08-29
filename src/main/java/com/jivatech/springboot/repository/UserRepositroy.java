package com.jivatech.springboot.repository;

import com.jivatech.springboot.Entry.Entry;
import com.jivatech.springboot.Entry.User;
import com.sun.source.tree.BreakTree;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositroy extends MongoRepository<User, ObjectId> {
   User findByUserName(String userName);
}
