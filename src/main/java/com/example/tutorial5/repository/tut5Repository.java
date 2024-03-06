package com.example.tutorial5.repository;

import com.example.tutorial5.request.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tut5Repository extends MongoRepository<User, String> {
}
