package com.placesware.eventsiowa.controller;

import java.util.List;

import org.springframework.data.geo.Polygon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "event", path = "event")
public interface EventRepository extends CrudRepository<Event, Long>, CustomEventRepository {

//	@PreAuthorize("hasRole('ADMIN')")
    public List<Event> findInPolygon(@Param("") Polygon polygon);

}