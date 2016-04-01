package com.placesware.eventsiowa.controller.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class EventRepositoryImpl implements CustomEventRepository {

	private final MongoOperations operations;

	  @Autowired
	  public EventRepositoryImpl(MongoOperations operations) {
	    Assert.notNull(operations, "MongoOperations must not be null!");
	    this.operations = operations;
	  }

	@Override
	@RequestMapping(value = "/findinpolygon", method = RequestMethod.POST)
	@PreAuthorize("@customAuthorization.isDataOwner(authentication, 'hello')")
	public List<Event> findInPolygon(@RequestBody Polygon polygon) {
		
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