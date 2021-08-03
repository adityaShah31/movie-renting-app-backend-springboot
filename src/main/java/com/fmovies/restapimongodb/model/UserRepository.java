package com.fmovies.restapimongodb.model;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAndPassword(String email, String passwordHash);
    User findByEmail(String email);
}
