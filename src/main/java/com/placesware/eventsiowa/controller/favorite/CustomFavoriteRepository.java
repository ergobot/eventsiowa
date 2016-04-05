package com.placesware.eventsiowa.controller.favorite;

import com.placesware.eventsiowa.controller.event.domain.Event;
import com.placesware.eventsiowa.controller.user.User;
import org.springframework.data.geo.Polygon;

import java.util.List;

public interface CustomFavoriteRepository {

	public List<Favorite> findFavorites();
	public List<Event> findInPolygon(Polygon polygon);

	public List<Event> getFavorites(User user);


	public void insertFavorite(Favorite favorite);
	public void removeFavorite(Favorite favorite);

}