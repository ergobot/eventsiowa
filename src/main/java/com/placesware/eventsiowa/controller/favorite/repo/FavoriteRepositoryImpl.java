package com.placesware.eventsiowa.controller.favorite.repo;

import com.placesware.eventsiowa.controller.event.domain.Event;
import com.placesware.eventsiowa.controller.favorite.domain.Favorite;
import com.placesware.eventsiowa.controller.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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




		return null;
	}

	@Override
	public List<Event> getFavorites(User user) {
		return null;
	}

	@Override
	public void insertFavorite(Favorite favorite) {

	}

	@Override
	public void removeFavorite(Favorite favorite) {

	}
}