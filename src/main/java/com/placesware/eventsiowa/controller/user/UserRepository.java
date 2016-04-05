package com.placesware.eventsiowa.controller.user;

import com.placesware.eventsiowa.controller.event.domain.Event;
import org.springframework.data.geo.Polygon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "event", path = "event")
public interface UserRepository extends CrudRepository<Event, Long>, CustomUserRepository {

//    @PreAuthorize("@customAuthorization.isDataOwner(authentication, )")
    public List<Event> findInPolygon(@Param("") Polygon polygon);

}