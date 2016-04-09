package com.placesware.eventsiowa.user.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.Assert;


public class UserRepositoryImpl implements CustomUserRepository {

	private final MongoOperations operations;

	  @Autowired
	  public UserRepositoryImpl(MongoOperations operations) {

	    Assert.notNull(operations, "MongoOperations must not be null!");
	    this.operations = operations;
	  }


}