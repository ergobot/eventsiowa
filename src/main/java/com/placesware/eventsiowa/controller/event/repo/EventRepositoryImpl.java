package com.placesware.eventsiowa.controller.event.repo;

import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.placesware.eventsiowa.controller.event.domain.Event;
import com.placesware.eventsiowa.controller.event.domain.EventPolygonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;

public class EventRepositoryImpl implements CustomEventRepository {

	private final MongoOperations operations;

	public List<Event> findEvents(QueryBuilder query){
		return findEvents(query.get());
	}

	// query database (find)
	public List<Event> findEvents(DBObject query) {
		BasicQuery q = new BasicQuery(query);

		List<Event> results = operations.find(q, Event.class);
		return results;
	}

	  @Autowired
	  public EventRepositoryImpl(MongoOperations operations) {
	    Assert.notNull(operations, "MongoOperations must not be null!");
	    this.operations = operations;
	  }

}