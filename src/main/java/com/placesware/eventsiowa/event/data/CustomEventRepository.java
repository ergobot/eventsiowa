package com.placesware.eventsiowa.event.data;

import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.placesware.eventsiowa.event.domain.Event;

public interface CustomEventRepository {

	public List<Event> findEvents(QueryBuilder query);

	// query database (find)
	public List<Event> findEvents(DBObject query);

}