package edu.nju.cineplex.action.business;

import java.util.HashMap;

public class ActivityToJoinBean {
	private String question;
	private String activityId;
	private HashMap<String, String> hashMap=new HashMap<String, String>();
	public HashMap<String, String> getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap<String, String> hashMap) {
		this.hashMap = hashMap;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	

}
