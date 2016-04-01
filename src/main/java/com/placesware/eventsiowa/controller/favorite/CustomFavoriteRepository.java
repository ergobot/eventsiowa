package com.placesware.eventsiowa.controller.favorite;

import com.placesware.eventsiowa.controller.event.Event;
import org.springframework.data.geo.Polygon;

import java.util.List;

public interface CustomFavoriteRepository {

	public List<Event> findInPolygon(Polygon polygon);

}