package com.placesware.eventsiowa.event.data;

import com.placesware.eventsiowa.event.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;


public interface EventRepository extends CrudRepository<Event, Long>, CustomEventRepository {

    public Event findById(@RequestBody String id);

    public Event findByLink(@RequestBody String link);

}