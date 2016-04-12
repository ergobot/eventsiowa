package com.placesware.eventsiowa.favorite;

import com.placesware.eventsiowa.event.domain.Event;
import com.placesware.eventsiowa.event.query.EventQueryBuilder;
import com.placesware.eventsiowa.event.data.EventRepository;
import com.placesware.eventsiowa.favorite.domain.Favorite;
import com.placesware.eventsiowa.favorite.data.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.POST,path="/getfavoriteevents")
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
