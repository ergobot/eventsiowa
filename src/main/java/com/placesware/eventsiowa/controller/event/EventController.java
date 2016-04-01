package com.placesware.eventsiowa.controller.event;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.POST,path = "/findAll")
    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public List<Event> findAll(@RequestBody String id){

        Iterable<Event> event = eventRepository.findAll();

        ArrayList<Event> events = new ArrayList<Event>();
        for(Event ev : event){
            events.add(ev);
        }

        return events;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/geteventsbytext")
    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public List<Event> getEventsByText(EventTextRequest request) {


//        Token.Validate(request.getClientId());

        QueryBuilder dateQuery = EventQueryBuilder.getEventsQueryByDate(request.getStartDateFilter(), request.getEndDateFilter());
        DBObject textQuery = EventQueryBuilder.getEventsByTextQuery(request.getSearchText());
        QueryBuilder query = new QueryBuilder();
        query.and(dateQuery.get(),textQuery);
        List<Event> results = eventRepository.findEvents(query);
        return results;
    }


    @RequestMapping(method = RequestMethod.POST,path = "/geteventsinpolygon")
    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public List<Event> getEventsInPolygon(EventPolygonRequest request) {

//        Token.Validate(request.getClientId());

        QueryBuilder dateQuery = EventQueryBuilder.getEventsQueryByDate(request.getStartDateFilter(), request.getEndDateFilter());
        BasicDBObject polygonQuery = EventQueryBuilder.getEventsInPolygonQuery(request.getPolygon());
        QueryBuilder query = new QueryBuilder();
        query.and(dateQuery.get(),polygonQuery);

        List<Event> results = eventRepository.findEvents(query);
        return results;

    }



}