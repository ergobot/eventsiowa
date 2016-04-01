package com.placesware.eventsiowa.controller.event;

import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
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

	@Override
//	@RequestMapping(value = "/findinpolygon", method = RequestMethod.POST)
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

	public List<Event> findEvents(QueryBuilder query){
		return findEvents(query.get());
	}

	// query database (find)
	public List<Event> findEvents(DBObject query) {
		Query q = (Query)query;

		List<Event> results = operations.find(q, Event.class);
		return results;
	}


	  @Autowired
	  public EventRepositoryImpl(MongoOperations operations) {
	    Assert.notNull(operations, "MongoOperations must not be null!");
	    this.operations = operations;
	  }

}