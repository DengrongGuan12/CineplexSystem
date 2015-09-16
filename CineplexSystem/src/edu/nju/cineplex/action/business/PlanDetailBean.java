package edu.nju.cineplex.action.business;

import edu.nju.cineplex.model.Hall;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Plan;

public class PlanDetailBean {
	private Plan plan;
	private Movie movie;
	private Hall hall;
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}

}
