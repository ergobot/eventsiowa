package com.placesware.eventsiowa.favorite.query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.placesware.eventsiowa.user.domain.User;

public class FavoriteQueryBuilder {

    public DBObject getFavoritesByUser(User user){
        BasicDBObject subQuery = new BasicDBObject();
        subQuery.append("sub", user.getSub());
        return subQuery;
    }

}
