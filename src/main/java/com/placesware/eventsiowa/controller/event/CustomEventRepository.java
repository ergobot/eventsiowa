package com.placesware.eventsiowa.controller.event;

import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import org.springframework.data.geo.Polygon;

public interface CustomEventRepository {

	public List<Event> findInPolygon(Polygon polygon);

	public List<Event> findEvents(QueryBuilder query);

	// query database (find)
	public List<Event> findEvents(DBObject query);

}