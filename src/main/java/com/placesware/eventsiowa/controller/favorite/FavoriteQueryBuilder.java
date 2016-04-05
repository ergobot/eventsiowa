package com.placesware.eventsiowa.controller.favorite;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.placesware.eventsiowa.controller.event.repo.EventRepository;
import com.placesware.eventsiowa.controller.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FavoriteQueryBuilder {


    @Autowired
    EventRepository eventRepository;

    public static DBObject getFavoritesQuery(User user){
        BasicDBObject subQuery = new BasicDBObject();
        subQuery.append("sub", user.getSub());

        List<Favorite> favorites = null; //EventDatabase.findFavorites(subQuery);
        ArrayList<String> eventIds = new ArrayList<String>();

        for(Favorite f : favorites){
            eventIds.add(f.getEventId());
        }

        BasicDBList docIds = new BasicDBList();

        docIds.addAll(eventIds);
        DBObject inClause = new BasicDBObject("$in", docIds);
        DBObject query = new BasicDBObject("_id", inClause);
//            List<Event> favoriteEvents = EventDatabase.findEvents(query);

        return query;
    }

}
