package edu.nju.cineplex.action.business;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Plan;

public class MoviePlanBean {
	private Movie movie;
	private boolean hasEnd=false;//有无下线
	public boolean isHasEnd() {
		return hasEnd;
	}
	//判断该电影是否下线
	public void setHasEnd() {
		Timestamp timestamp=movie.getEndTime();
		Date date=new Date();
		if(timestamp.compareTo(date)<0){
			this.hasEnd=true;
		}
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	private List<Plan> plans;
	public List<Plan> getPlans() {
		return plans;
	}
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
	
}
