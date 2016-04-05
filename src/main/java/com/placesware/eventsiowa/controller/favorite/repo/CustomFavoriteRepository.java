package com.placesware.eventsiowa.controller.favorite.repo;

import com.placesware.eventsiowa.controller.event.domain.Event;
import com.placesware.eventsiowa.controller.favorite.domain.Favorite;
import com.placesware.eventsiowa.controller.user.User;

import java.util.List;

public interface CustomFavoriteRepository {

	public List<Favorite> findFavorites();

	public List<Event> getFavorites(User user);


	public void insertFavorite(Favorite favorite);
	public void removeFavorite(Favorite favorite);

}