package com.placesware.eventsiowa.controller.favorite;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.placesware.eventsiowa.controller.event.domain.Event;
import com.placesware.eventsiowa.controller.event.domain.EventPolygonRequest;
import com.placesware.eventsiowa.controller.event.domain.EventTextRequest;
import com.placesware.eventsiowa.controller.event.query.EventQueryBuilder;
import com.placesware.eventsiowa.controller.event.repo.EventRepository;
import com.placesware.eventsiowa.controller.favorite.domain.Favorite;
import com.placesware.eventsiowa.controller.favorite.repo.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

//import java.util.Iterator;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.POST,path="/getfavoriteevents")
//    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public List<Event> getFavoriteEvents(){
        List<Favorite> favorites = favoriteRepository.findFavorites();
        EventQueryBuilder eventQueryBuilder = new EventQueryBuilder();
        ArrayList<String> eventIds = new ArrayList<String>();
        for(Favorite favorite : favorites){
            eventIds.add(favorite.getEventId());
        }
        List<Event> events = eventRepository.findEvents(eventQueryBuilder.getEventsByIds(eventIds));
        return events;
    }



    @RequestMapping(method = RequestMethod.POST,path="/save")
    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public void save(@RequestBody Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    @RequestMapping(method = RequestMethod.POST,path="/delete")
    @PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
    public void delete(@RequestBody Favorite favorite) {
        favoriteRepository.delete(favorite);
    }





}
