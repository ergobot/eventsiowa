package com.placesware.eventsiowa.controller.favorite.repo;

import com.placesware.eventsiowa.controller.event.domain.Event;
import org.springframework.data.repository.CrudRepository;


//@RepositoryRestResource(collectionResourceRel = "favorite", path = "favorite")
public interface FavoriteRepository extends CrudRepository<Event, Long>, CustomFavoriteRepository {


}