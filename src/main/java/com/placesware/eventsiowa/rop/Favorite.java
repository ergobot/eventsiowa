package com.placesware.eventsiowa.rop;

public class Favorite {

//	private ObjectId _id;
	private String sub;
	private String eventId;
//	public ObjectId get_id() {
//		return _id;
//	}
//	public void set_id(ObjectId _id) {
//		this._id = _id;
//	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getEventId() {
		return eventId;
	}
//	public void setEventId(ObjectId eventId) {
//		this.eventId = eventId;
//	}
	public void setEventId(String eventId){
		this.eventId = eventId;
	}
	
	
}
