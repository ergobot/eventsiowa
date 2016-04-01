package com.placesware.eventsiowa.controller.favorite;

public class FavoriteQueryBuilder {

    public static DBObject getFavoritesQuery(User user){
        BasicDBObject subQuery = new BasicDBObject();
        subQuery.append("sub", user.getSub());

        List<Favorite> favorites = EventDatabase.findFavorites(subQuery);
        ArrayList<ObjectId> eventIds = new ArrayList<ObjectId>();

        for(Favorite f : favorites){
            eventIds.add(new ObjectId(f.getEventId()));
        }

        BasicDBList docIds = new BasicDBList();

        docIds.addAll(eventIds);
        DBObject inClause = new BasicDBObject("$in", docIds);
        DBObject query = new BasicDBObject("_id", inClause);
//            List<Event> favoriteEvents = EventDatabase.findEvents(query);

        return query;
    }

}
