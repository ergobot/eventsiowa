package com.placesware.eventsiowa.security;

import com.placesware.eventsiowa.controller.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findBySub(String sub);
    public User findByEmail(String email);
    public User findById(String id);

}
