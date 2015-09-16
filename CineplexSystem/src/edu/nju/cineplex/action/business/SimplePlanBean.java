package edu.nju.cineplex.action.business;

import edu.nju.cineplex.enums.MoviePlayState;

public class SimplePlanBean {
	private String price;
	private String movieName;
	private String planId;
	private String movieId;

	private MoviePlayState moviePlayState;
	
	public MoviePlayState getMoviePlayState() {
		return moviePlayState;
	}
	public void setMoviePlayState(MoviePlayState moviePlayState) {
		this.moviePlayState = moviePlayState;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	

}
