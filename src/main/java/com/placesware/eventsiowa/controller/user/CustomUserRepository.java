package com.placesware.eventsiowa.controller.user;

import com.placesware.eventsiowa.controller.event.domain.Event;
import org.springframework.data.geo.Polygon;

import java.util.List;

public interface CustomUserRepository {

	public List<Event> findInPolygon(Polygon polygon);

}