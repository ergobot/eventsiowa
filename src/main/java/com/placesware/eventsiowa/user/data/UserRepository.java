package com.placesware.eventsiowa.user.data;

import com.placesware.eventsiowa.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>, CustomUserRepository {

    public User findBySub(String sub);
    public User findByEmail(String email);
    public User findById(String id);

}