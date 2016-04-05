package com.placesware.eventsiowa.controller.favorite.domain;

import org.springframework.data.annotation.Id;

public class Favorite {



	@Id
	private String id;
	private String sub;
	private String eventId;
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId){this.eventId = eventId;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
}
