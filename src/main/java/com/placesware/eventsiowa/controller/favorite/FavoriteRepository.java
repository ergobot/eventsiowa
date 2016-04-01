package com.placesware.eventsiowa.controller.favorite;

import com.placesware.eventsiowa.controller.event.Event;
import org.springframework.data.geo.Polygon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "event", path = "event")
public interface FavoriteRepository extends CrudRepository<Event, Long>, CustomFavoriteRepository {

//    @PreAuthorize("@customAuthorization.isDataOwner(authentication, )")
    public List<Event> findInPolygon(@Param("") Polygon polygon);




}