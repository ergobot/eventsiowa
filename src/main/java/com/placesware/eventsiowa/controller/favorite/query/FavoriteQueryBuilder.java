package com.placesware.eventsiowa.controller.favorite.query;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.placesware.eventsiowa.controller.event.repo.EventRepository;
import com.placesware.eventsiowa.controller.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FavoriteQueryBuilder {

    public static DBObject getFavoritesQuery(User user){
        BasicDBObject subQuery = new BasicDBObject();
        subQuery.append("sub", user.getSub());
        return subQuery;
    }

}
