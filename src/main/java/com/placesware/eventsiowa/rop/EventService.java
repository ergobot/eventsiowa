package com.placesware.eventsiowa.rop;

//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import com.mongodb.QueryBuilder;
//import com.placesware.db.EventDatabase;
//import com.placesware.domain.Event;
//import com.placesware.exception.AuthenticationException;
//import com.placesware.service.request.EventPolygonRequest;
//import com.placesware.service.request.EventRadiusRequest;
//import com.placesware.service.request.EventTextRequest;
//
//@Path("event")
public class EventService {
//
//	public static EventService INSTANCE = new EventService();
//
//	private EventService(){}
//
//	@POST
//	@Path("geteventsbytext")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> getEventsByText(EventTextRequest request) throws AuthenticationException{
//
//
//		Token.Validate(request.getClientId());
//
//		QueryBuilder dateQuery = EventDatabase.getEventsQueryByDate(request.getStartDateFilter(), request.getEndDateFilter());
//		DBObject textQuery = EventDatabase.getEventsByTextQuery(request.getSearchText());
//		QueryBuilder query = new QueryBuilder();
//		query.and(dateQuery.get(),textQuery);
//		List<Event> results = EventDatabase.findEvents(query);
//		return results;
//	}
//
//
//	@POST
//	@Path("geteventsinpolygon")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> getEventsInPolygon(EventPolygonRequest request) throws AuthenticationException{
//
//		Token.Validate(request.getClientId());
//
//		QueryBuilder dateQuery = EventDatabase.getEventsQueryByDate(request.getStartDateFilter(), request.getEndDateFilter());
//		BasicDBObject polygonQuery = EventDatabase.getEventsInPolygonQuery(request.getPolygon());
//		QueryBuilder query = new QueryBuilder();
//		query.and(dateQuery.get(),polygonQuery);
//
//		List<Event> results = EventDatabase.findEvents(query);
//		return results;
//
//	}
//
//
//	@POST
//	@Path("signin")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public SignInResponse signIn(SignInRequest request) throws AuthenticationException{
//		System.out.println("Attempting signin");
//		Token.ValidateUser(request.getClientId());
//		System.out.println("Signin: " + request.getClientId());
//		SignInResponse response = new SignInResponse();
//		response.setSuccess(true);
//		return response;
//	}
//
//
//	@POST
//	@Path("getfavorites")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> getFavorites(SignInRequest request) throws AuthenticationException{
//		System.out.println("Attempting signin");
//		User user = Token.ValidateAndGetUser(request.getClientId());
//		System.out.println("Signin: " + request.getClientId());
//
//		List<Event> favorites = EventDatabase.getFavorites(user);
//
//		return favorites;
//
//	}
//
//	@POST
//	@Path("getfavoriteeventsinpolygon")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> getFavoriteEventsInPolygon(EventPolygonRequest request) throws AuthenticationException{
//
////		Token.Validate(request.getClientId());
//		User user = Token.ValidateAndGetUser(request.getClientId());
//		QueryBuilder dateQuery = EventDatabase.getEventsQueryByDate(request.getStartDateFilter(), request.getEndDateFilter());
//		BasicDBObject polygonQuery = EventDatabase.getEventsInPolygonQuery(request.getPolygon());
//		DBObject favoritesQuery = EventDatabase.getFavoritesQuery(user);
//		QueryBuilder query = new QueryBuilder();
//		query.and(dateQuery.get(),polygonQuery,favoritesQuery);
//
//		List<Event> results = EventDatabase.findEvents(query);
//		return results;
//
//	}
//
//	@POST
//	@Path("getfavoriteeventsbytext")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> getFavoriteEventsByText(EventTextRequest request) throws AuthenticationException{
//
//
////		Token.Validate(request.getClientId());
//		User user = Token.ValidateAndGetUser(request.getClientId());
//
//		QueryBuilder dateQuery = EventDatabase.getEventsQueryByDate(request.getStartDateFilter(), request.getEndDateFilter());
//		DBObject textQuery = EventDatabase.getEventsByTextQuery(request.getSearchText());
//		QueryBuilder query = new QueryBuilder();
//		DBObject favoritesQuery = EventDatabase.getFavoritesQuery(user);
//		query.and(dateQuery.get(),textQuery,favoritesQuery);
//		List<Event> results = EventDatabase.findEvents(query);
//		return results;
//	}
//
//
//	@POST
//	@Path("setfavorite")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> setFavorite(FavoriteRequest request) throws AuthenticationException{
//		System.out.println("Attempting signin");
//
//		User user = Token.ValidateAndGetUser(request.getClientId());
//		System.out.println("Signin: " + request.getClientId());
//
//		request.getFavorite().setSub(user.getSub());
//
//		EventDatabase.insertFavoriteJson(request.getFavorite());
//
//		List<Event> favorites = EventDatabase.getFavorites(user);
//
//		return favorites;
//
//	}
//
//	@POST
//	@Path("retrievefavorites")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> retrieveFavorite(SignInRequest request) throws AuthenticationException{
//		System.out.println("Attempting signin");
//
//		User user = Token.ValidateAndGetUser(request.getClientId());
//		System.out.println("Signin: " + request.getClientId());
//
//		List<Event> favorites = EventDatabase.getFavorites(user);
//
//		return favorites;
//
//	}
//
//	@POST
//	@Path("removefavorite")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> removeFavorite(FavoriteRequest request) throws AuthenticationException{
//		System.out.println("Attempting signin");
//
//		User user = Token.ValidateAndGetUser(request.getClientId());
//		System.out.println("Signin: " + request.getClientId());
//
//		request.getFavorite().setSub(user.getSub());
//
//		EventDatabase.removeFavoriteJson(request.getFavorite());
//
//		List<Event> favorites = EventDatabase.getFavorites(user);
//
//		return favorites;
//
//	}
//
//
//
//	@POST
//	@Path("geteventsinradius")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> getEventsInRadius(EventRadiusRequest request) throws AuthenticationException{
//
//		Token.Validate(request.getClientId());
//
//		BasicDBObject query = EventDatabase.getEventsInRadiusQuery(request);
//		List<Event> result = EventDatabase.command(query);
//		return result;
//
//	}
//
//
//
//	@GET
//	@Path("echo")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Event> echo() throws AuthenticationException{
//		double[] loc = { -93.60910, 41.60054 };
//		List<Double> coord = new ArrayList<Double>();
//		coord.add(loc[0]);
//		coord.add(loc[1]);
//		double radius = 400000;
//
//		EventRadiusRequest request = new EventRadiusRequest(coord,radius);
//
//		return getEventsInRadius(request);
//	}
//
//	@POST
//	@Path("echofavorites")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Event> getFavorites(FavoriteRequest favoriteRequest) throws AuthenticationException{
//		System.out.println("Attempting signin");
////		User user = Token.ValidateAndGetUser(favoriteRequest.getClientId());
//		User user = new User();
//		user.setSub("100801439712078253931");
//		user.setEmail("globalw2865@gmail.com");
//		user.setNotificationFrequency("0");
//		user.setNotify(false);
////		System.out.println("Signin: " + favoriteRequest.getClientId());
//
//		EventDatabase.insertFavoriteJson(favoriteRequest.getFavorite());
//
//		List<Event> favorites = EventDatabase.getFavorites(user);
//
//		return favorites;
//
//	}
//
	
}
