package com.placesware.eventsiowa.favorite.data;

import com.placesware.eventsiowa.favorite.domain.Favorite;
import com.placesware.eventsiowa.user.domain.User;

import java.util.List;

public interface CustomFavoriteRepository {

	public List<Favorite> findFavorites();

	public List<Favorite> getFavorites(User user);

}