package com.placesware.eventsiowa.controller.favorite;

import com.placesware.eventsiowa.controller.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import java.util.List;

public class FavoriteRepositoryImpl implements CustomFavoriteRepository {

	private final MongoOperations operations;

	  @Autowired
	  public FavoriteRepositoryImpl(MongoOperations operations) {

	    Assert.notNull(operations, "MongoOperations must not be null!");
	    this.operations = operations;
	  }


	@Override
	public List<Event> findInPolygon(Polygon polygon) {
		
//		BasicDBObject query = new BasicDBObject("point", new BasicDBObject("$within",
//				 new BasicDBObject("$polygon", polygon)));
		
		Criteria c = new Criteria();
		c.within(polygon);
		Query query = new Query();
		query.addCriteria(c);
		List<Event> results = operations.find(query, Event.class);
		return results;
		
		
	
	}

}