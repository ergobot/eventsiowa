package com.placesware.eventsiowa.controller.favorite.repo;

import com.placesware.eventsiowa.controller.favorite.domain.Favorite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Long>, CustomFavoriteRepository {

    public List<Favorite> findBySub(String sub);

}