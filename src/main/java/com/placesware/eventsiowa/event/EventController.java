package com.placesware.eventsiowa.event;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.placesware.eventsiowa.event.domain.Event;
import com.placesware.eventsiowa.event.domain.EventPolygonRequest;
import com.placesware.eventsiowa.event.domain.EventTextRequest;
import com.placesware.eventsiowa.event.query.EventQueryBuilder;
import com.placesware.eventsiowa.event.data.EventRepository;
import com.placesware.eventsiowa.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.POST,path = "/findall")
//    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public List<Event> findAll(@RequestBody String id){

        Iterable<Event> event = eventRepository.findAll();

        ArrayList<Event> events = new ArrayList<Event>();
        for(Event ev : event){
            events.add(ev);
        }

        return events;
    }

    @RequestMapping(method = RequestMethod.GET, path="/echo")
    public String echo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return "Hello world: ";
    }

    @RequestMapping(method = RequestMethod.POST,path = "/findbyids")
    public List<Event> findByIds(@RequestBody List<String> ids){

        EventQueryBuilder eventsQueryBuilder = new EventQueryBuilder();
        DBObject query = eventsQueryBuilder.getEventsByIds(ids);
        List<Event> events = eventRepository.findEvents(query);

        return events;
    }


    @RequestMapping(method = RequestMethod.POST,path = "/geteventsbytext")
//    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public List<Event> getEventsByText(@RequestBody EventTextRequest request) {


//        Token.Validate(request.getClientId());

        QueryBuilder dateQuery = EventQueryBuilder.getEventsQueryByDate(request.getStartDateFilter(), request.getEndDateFilter());
        DBObject textQuery = EventQueryBuilder.getEventsByTextQuery(request.getSearchText());
        QueryBuilder query = new QueryBuilder();
        query.and(dateQuery.get(),textQuery);
        List<Event> results = eventRepository.findEvents(query);
        return results;
    }


    @RequestMapping(method = RequestMethod.POST,path = "/geteventsinpolygon")
//    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public List<Event> getEventsInPolygon(@RequestBody EventPolygonRequest request) {

//        Token.Validate(request.getClientId());

        QueryBuilder dateQuery = EventQueryBuilder.getEventsQueryByDate(request.getDateFilterStart(), request.getDateFilterEnd());
        BasicDBObject polygonQuery = EventQueryBuilder.getEventsInPolygonQuery(request.getPolygon());
        QueryBuilder query = new QueryBuilder();
        query.and(dateQuery.get(),polygonQuery);
        List<Event> results = eventRepository.findEvents(query);
        return results;

    }


    @RequestMapping(method = RequestMethod.POST,path="/save")
    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public void save(@RequestBody Event event) {
        eventRepository.save(event);
    }

    @RequestMapping(method = RequestMethod.POST,path="/delete")
    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public void delete(@RequestBody Event event) {
        eventRepository.delete(event);
    }



}
