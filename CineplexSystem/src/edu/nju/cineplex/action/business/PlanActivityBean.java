package edu.nju.cineplex.action.business;


public class PlanActivityBean {
	private String planId;
	private String movieName;
	private String movieOfflineTime;
	private String question;
	private boolean ifOffLine;
	
	public boolean isIfOffLine() {
		return ifOffLine;
	}
	public void setIfOffLine(boolean ifOffLine) {
		this.ifOffLine = ifOffLine;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieOfflineTime() {
		return movieOfflineTime;
	}
	public void setMovieOfflineTime(String movieOfflineTime) {
		this.movieOfflineTime = movieOfflineTime;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	

}
