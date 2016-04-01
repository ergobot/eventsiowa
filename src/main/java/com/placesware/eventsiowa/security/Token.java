package com.placesware.eventsiowa.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.placesware.eventsiowa.controller.user.User;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class Token {


	private static String CLIENT_ID = "";


	public static void Validate(String idTokenString) throws InternalAuthenticationServiceException{
		
		if(idTokenString == null){
			throw new InternalAuthenticationServiceException("Client token is empty");
		}
		if(idTokenString.isEmpty()){
			throw new InternalAuthenticationServiceException("Client token is empty");
		}
		
		NetHttpTransport transport = new NetHttpTransport();
		GsonFactory jsonFactory = new GsonFactory();
//
//
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
			    .setAudience(Arrays.asList(CLIENT_ID))
//			    // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
//			    // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
//			    // "accounts.google.com". If you need to verify tokens from multiple sources, build
//			    // a GoogleIdTokenVerifier for each issuer and try them both.
			    .setIssuer("https://accounts.google.com")
			    .build();
//
		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(idTokenString);
		} catch (GeneralSecurityException | IOException e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
////			throw new AuthenticationException("Unable to authenticate");
			throw new InternalAuthenticationServiceException("Unable to authenticate");
		}
//
//
		if (idToken != null) {
		  Payload payload = idToken.getPayload();
//
//		  // Print user identifier
		  String userId = payload.getSubject();
//
//		  // Get profile information from payload
		  String email = payload.getEmail();
		  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		  String name = (String) payload.get("name");
		  String pictureUrl = (String) payload.get("picture");
		  String locale = (String) payload.get("locale");
		  String familyName = (String) payload.get("family_name");
		  String givenName = (String) payload.get("given_name");
//
		  System.out.println("User ID: " + userId);
		  System.out.println("Email: " + email);
		  System.out.println("Email verified: " + emailVerified);
		  System.out.println("Name: " + name);
		  System.out.println("PictureUrl: " + pictureUrl);
		  System.out.println("locale: " + locale);
		  System.out.println("Family Name: " + familyName);
		  System.out.println("Given Name: " + givenName);
		  // Use or store profile information
		  // ...
		} else {

		  System.out.println("Invalid ID token.");
		  throw new InternalAuthenticationServiceException("Unable to authenticate");
		}
		
		
	}
	
public static void ValidateUser(String idTokenString) throws InternalAuthenticationServiceException {
		
		if(idTokenString == null){
			 throw new InternalAuthenticationServiceException("Client token is empty");
		}
		if(idTokenString.isEmpty()){
			throw new InternalAuthenticationServiceException("Client token is empty");
		}
		
		NetHttpTransport transport = new NetHttpTransport();
		GsonFactory jsonFactory = new GsonFactory();

		User user = new User();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
			    .setAudience(Arrays.asList(CLIENT_ID))
			    // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
			    // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
			    // "accounts.google.com". If you need to verify tokens from multiple sources, build
			    // a GoogleIdTokenVerifier for each issuer and try them both.
			    .setIssuer("https://accounts.google.com")
			    .build();

		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(idTokenString);
		} catch (GeneralSecurityException | IOException e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InternalAuthenticationServiceException("Unable to authenticate");
		}


		if (idToken != null) {
		  Payload payload = idToken.getPayload();

		  // Print user identifier
		  String userId = payload.getSubject();

		  // Get profile information from payload
		  String email = payload.getEmail();
		  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		  String name = (String) payload.get("name");
		  String pictureUrl = (String) payload.get("picture");
		  String locale = (String) payload.get("locale");
		  String familyName = (String) payload.get("family_name");
		  String givenName = (String) payload.get("given_name");

		  System.out.println("User ID: " + userId);
		  System.out.println("Email: " + email);
		  System.out.println("Email verified: " + emailVerified);
		  System.out.println("Name: " + name);
		  System.out.println("PictureUrl: " + pictureUrl);
		  System.out.println("locale: " + locale);
		  System.out.println("Family Name: " + familyName);
		  System.out.println("Given Name: " + givenName);
		  // Use or store profile information
		  // ...
		  user.setSub(userId);
		  user.setEmail(email);

//		  EventDatabase.insertUserJson(user);

		} else {

		  System.out.println("Invalid ID token.");
		  throw new InternalAuthenticationServiceException("Unable to authenticate");
		}
		
	}

public static User ValidateAndGetUser(String idTokenString) throws InternalAuthenticationServiceException{
	
	if(idTokenString == null){
		 throw new InternalAuthenticationServiceException("Client token is empty");
	}
	if(idTokenString.isEmpty()){
		throw new InternalAuthenticationServiceException("Client token is empty");
	}
	
	NetHttpTransport transport = new NetHttpTransport();
	GsonFactory jsonFactory = new GsonFactory();

	User user = new User();

	GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
		    .setAudience(Arrays.asList(CLIENT_ID))
		    // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
		    // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
		    // "accounts.google.com". If you need to verify tokens from multiple sources, build
		    // a GoogleIdTokenVerifier for each issuer and try them both.
		    .setIssuer("https://accounts.google.com")
		    .build();

	GoogleIdToken idToken = null;
	try {
		idToken = verifier.verify(idTokenString);
	} catch (GeneralSecurityException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new InternalAuthenticationServiceException("Unable to authenticate");
	}


	if (idToken != null) {
	  Payload payload = idToken.getPayload();

	  // Print user identifier
	  String userId = payload.getSubject();

	  // Get profile information from payload
	  String email = payload.getEmail();
	  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
	  String name = (String) payload.get("name");
	  String pictureUrl = (String) payload.get("picture");
	  String locale = (String) payload.get("locale");
	  String familyName = (String) payload.get("family_name");
	  String givenName = (String) payload.get("given_name");

	  System.out.println("User ID: " + userId);
	  System.out.println("Email: " + email);
	  System.out.println("Email verified: " + emailVerified);
	  System.out.println("Name: " + name);
	  System.out.println("PictureUrl: " + pictureUrl);
	  System.out.println("locale: " + locale);
	  System.out.println("Family Name: " + familyName);
	  System.out.println("Given Name: " + givenName);
	  // Use or store profile information
	  // ...
	  user.setSub(userId);
	  user.setEmail(email);

//	  EventDatabase.insertUserJson(user);

	} else {

	  System.out.println("Invalid ID token.");
	  throw new InternalAuthenticationServiceException("Unable to authenticate");
	}
	return user;
}


	
}
