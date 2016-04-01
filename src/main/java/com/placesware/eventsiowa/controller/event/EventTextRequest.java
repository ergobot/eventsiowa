package com.placesware.eventsiowa.controller.event;


public class EventTextRequest {

	private String searchText;
	private long startDateFilter;
	private long endDateFilter;
	
	public EventTextRequest(){}
	
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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}


	
}
