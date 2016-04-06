package com.placesware.eventsiowa.controller.favorite.repo;

import com.placesware.eventsiowa.controller.event.domain.Event;
import com.placesware.eventsiowa.controller.favorite.domain.Favorite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//@RepositoryRestResource(collectionResourceRel = "favorite", path = "favorite")
public interface FavoriteRepository extends CrudRepository<Favorite, Long>, CustomFavoriteRepository {


    public List<Favorite> findBySub(String sub);


}