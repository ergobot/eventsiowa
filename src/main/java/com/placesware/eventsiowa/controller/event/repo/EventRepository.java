package com.placesware.eventsiowa.controller.event.repo;

import com.placesware.eventsiowa.controller.event.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;


public interface EventRepository extends CrudRepository<Event, Long>, CustomEventRepository {

    public Event findById(@RequestBody String id);

    public Event findByLink(@RequestBody String link);

}