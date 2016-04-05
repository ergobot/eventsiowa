package com.placesware.eventsiowa.controller.event.repo;

import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.placesware.eventsiowa.controller.event.domain.Event;
import com.placesware.eventsiowa.controller.event.domain.EventPolygonRequest;
import org.springframework.data.geo.Polygon;

public interface CustomEventRepository {

	public List<Event> findEvents(QueryBuilder query);

	// query database (find)
	public List<Event> findEvents(DBObject query);

}