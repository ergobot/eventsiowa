package com.placesware.eventsiowa.controller.event.query;

import org.joda.time.DateTime;
import org.joda.time.Days;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by morbo on 3/31/16.
 */
public class EventQueryBuilder {

//    // getPolygon query
    public static BasicDBObject getEventsInPolygonQuery(Polygon polygon) {
        // ***Keep this query ***
        BasicDBObject query = new BasicDBObject("point",new BasicDBObject("$within", new BasicDBObject("$polygon", polygon)));
        return query;
    }
    // getPolygon query
//    public static Query getEventsInPolygonQuery(Polygon polygon) {
//        Criteria c = new Criteria();
//        c.within(polygon);
//        Query query = new Query();
//        query.addCriteria(c);
//        return query;
//    }



    // getDateQuery
    public static QueryBuilder getEventsQueryByDate(long startDate, long endDate) {

        DateTime start = null;
        DateTime end = null;;
        DBObject query;
        QueryBuilder andQuery = new QueryBuilder();
        QueryBuilder startDateOrQuery = new QueryBuilder();
        QueryBuilder endDateOrQuery = new QueryBuilder();
        if (startDate > -1L && endDate > -1L) {
            // polygon+(events between startdate and enddate)
            start = new DateTime(startDate).withTimeAtStartOfDay();
            end = new DateTime(endDate).millisOfDay().withMaximumValue();
            // if range(start,date) > 30
            // then end is start+30
            if (Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays() > 30) {
                end = start.millisOfDay().withMaximumValue().plusDays(30);
            }

            System.out.println("1 query");

        } else if (startDate > -1L && endDate == -1L) {
            // polygon+(events between given startdate and end of givenstartdate)
            start = new DateTime(startDate).withTimeAtStartOfDay();
            end = new DateTime(startDate).millisOfDay().withMaximumValue();

            System.out.println("2 query");
        } else if (startDate == -1L && endDate > -1L) {

            // For now, this is the same query as 2 but with end date

            start = new DateTime(endDate).withTimeAtStartOfDay();
            end = new DateTime(endDate).millisOfDay().withMaximumValue();

            System.out.println("3 query");
        } else if (startDate == -1L && endDate == -1L) {
            // if(startMinute == -1 && endMinute == -1){
            // polygon+(events in today) (no filter)

            start = new DateTime().withTimeAtStartOfDay();
            end = new DateTime().millisOfDay().withMaximumValue();

            System.out.println("4 query");
        }

        System.out.println("Start Date: " + start.toString());
        System.out.println("End Date:   " + end.toString());

        // Get all events that start and end outside of range
        QueryBuilder s1 = new QueryBuilder();
        s1.and(QueryBuilder.start().put("startDate").lessThanEquals(start.toDate()).get(),
                QueryBuilder.start().put("endDate").greaterThanEquals(end.toDate()).get());

        // Get all events that start before range but end in range
        QueryBuilder s2 = new QueryBuilder();
        s2.and(QueryBuilder.start().put("startDate").lessThanEquals(start.toDate()).get(),
                QueryBuilder.start().put("endDate").greaterThanEquals(start.toDate()).get(),
                QueryBuilder.start().put("endDate").lessThanEquals(end.toDate()).get());

        // Get all events that start in range and end in range
        QueryBuilder s3 = new QueryBuilder();
        s3.and(QueryBuilder.start().put("startDate").greaterThanEquals(start.toDate()).get(),
                QueryBuilder.start().put("endDate").lessThanEquals(end.toDate()).get());

        // Get all events that start in range and end past range
        QueryBuilder s4 = new QueryBuilder();
        s4.and(QueryBuilder.start().put("startDate").greaterThanEquals(start.toDate()).get(),
                QueryBuilder.start().put("startDate").lessThanEquals(end.toDate()).get(),
                QueryBuilder.start().put("endDate").greaterThanEquals(end.toDate()).get());

        andQuery.or(s1.get(),
                s2.get(),
                s3.get(),
                s4.get());

        return andQuery;

    }




    // getTextQuery
    public static DBObject getEventsByTextQuery(String searchText){

        java.util.regex.Pattern searchQuery = java.util.regex.Pattern.compile(".*"+searchText.replace("\"", "")+".*");

        BasicDBObject titleQuery = new BasicDBObject();
        titleQuery.put("title", new BasicDBObject("$regex", searchQuery).append("$options", "i"));

        BasicDBObject locationQuery = new BasicDBObject();
        locationQuery.put("location", new BasicDBObject("$regex", searchQuery).append("$options", "i"));

        BasicDBObject detailsQuery = new BasicDBObject();
        detailsQuery.put("details", new BasicDBObject("$regex", searchQuery).append("$options", "i"));

//		BasicDBObject phonesQuery = new BasicDBObject();
//		phonesQuery.put("phones", new BasicDBObject("$regex", ".*"+searchText+".*").append("$options", "i"));
//
//		BasicDBObject emailsQuery = new BasicDBObject();
//		emailsQuery.put("emails", new BasicDBObject("$regex", ".*"+searchText+".*").append("$options", "i"));
//
//		BasicDBObject websitesQuery = new BasicDBObject();
//		websitesQuery.put("websites", new BasicDBObject("$regex", ".*"+searchText+".*").append("$options", "i"));

        BasicDBList or = new BasicDBList();
        or.add(titleQuery);
        or.add(locationQuery);
        or.add(detailsQuery);

        DBObject query = new BasicDBObject("$or", or);



        return query;
    }

    public DBObject getEventsByIds(List<String> ids){

        BasicDBList docIds = new BasicDBList();

        docIds.addAll(ids);
        DBObject inClause = new BasicDBObject("$in", docIds);
        DBObject query = new BasicDBObject("_id", inClause);

        return query;
    }


}
