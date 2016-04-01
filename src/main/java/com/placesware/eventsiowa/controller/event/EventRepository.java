package com.placesware.eventsiowa.controller.event;

//import java.util.List;

//import org.springframework.data.geo.Polygon;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RepositoryRestResource(collectionResourceRel = "event", path = "event")
public interface EventRepository extends CrudRepository<Event, Long>, CustomEventRepository {

    // Given Code
    @RequestMapping(value = "/findbyid", method = RequestMethod.POST)
    public Event findById(@RequestBody String id);

    @RequestMapping(value = "/findbylink", method = RequestMethod.POST)
    public Event findByLink(@RequestBody String link);



//    public List<Event> findInPolygon(@RequestBody Polygon polygon);

}