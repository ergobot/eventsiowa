package com.placesware.eventsiowa.event.domain;

import org.springframework.data.geo.Polygon;

public class EventPolygonRequest {

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public long getDateFilterStart() {
        return dateFilterStart;
    }

    public void setDateFilterStart(long dateFilterStart) {
        this.dateFilterStart = dateFilterStart;
    }

    public long getDateFilterEnd() {
        return dateFilterEnd;
    }

    public void setDateFilterEnd(long dateFilterEnd) {
        this.dateFilterEnd = dateFilterEnd;
    }

    private Polygon polygon;
    private long dateFilterStart;
    private long dateFilterEnd;




}