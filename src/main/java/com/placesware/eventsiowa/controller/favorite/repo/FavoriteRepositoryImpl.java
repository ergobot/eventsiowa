package com.placesware.eventsiowa.controller.favorite.repo;

import com.mongodb.DBObject;
import com.placesware.eventsiowa.controller.event.domain.Event;
import com.placesware.eventsiowa.controller.favorite.domain.Favorite;
import com.placesware.eventsiowa.controller.favorite.query.FavoriteQueryBuilder;
import com.placesware.eventsiowa.controller.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.util.List;

public class FavoriteRepositoryImpl implements CustomFavoriteRepository {

	private final MongoOperations operations;

	  @Autowired
	  public FavoriteRepositoryImpl(MongoOperations operations) {

	    Assert.notNull(operations, "MongoOperations must not be null!");
	    this.operations = operations;
	  }

	@Override
	public List<Favorite> findFavorites() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		FavoriteQueryBuilder favoriteQueryBuilder = new FavoriteQueryBuilder();
		DBObject dbObject = favoriteQueryBuilder.getFavoritesByUser(user);
		BasicQuery query = new BasicQuery(dbObject);
		List<Favorite> favorites = operations.find(query,Favorite.class);
		return favorites;
	}

	@Override
	public List<Favorite> getFavorites(User user) {
		FavoriteQueryBuilder favoriteQueryBuilder = new FavoriteQueryBuilder();
		DBObject dbObject = favoriteQueryBuilder.getFavoritesByUser(user);
		BasicQuery query = new BasicQuery(dbObject);
		List<Favorite> favorites = operations.find(query,Favorite.class);
		return favorites;
	}

}