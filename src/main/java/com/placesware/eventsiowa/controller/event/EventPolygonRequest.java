package com.placesware.eventsiowa.controller.event;

import java.util.List;

public class EventPolygonRequest {

	private List<Double[]> polygon;
	private long startDateFilter;
	private long endDateFilter;
	
	public EventPolygonRequest(){}
	
	public List<Double[]> getPolygon() {
		return polygon;
	}
	public void setPolygon(List<Double[]> polygon) {
		this.polygon = polygon;
	}
	public long getStartDateFilter() {
		return startDateFilter;
	}
	public void setStartDateFilter(long startDateFilter) {
		this.startDateFilter = startDateFilter;
	}
	public long getEndDateFilter() {
		return endDateFilter;
	}
	public void setEndDateFilter(long endDateFilter) {
		this.endDateFilter = endDateFilter;
	}

}
