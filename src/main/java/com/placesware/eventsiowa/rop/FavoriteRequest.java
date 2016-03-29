package com.placesware.eventsiowa.rop;

public class FavoriteRequest {

	private String clientId;
	private Favorite favorite;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Favorite getFavorite() {
		return favorite;
	}
	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}
	
}
