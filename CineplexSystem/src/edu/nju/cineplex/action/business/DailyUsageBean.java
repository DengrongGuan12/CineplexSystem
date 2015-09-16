package edu.nju.cineplex.action.business;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class DailyUsageBean {
	private String year;
	private String month;
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	private HashMap<Integer, Integer> dailyUsageMap=new LinkedHashMap<Integer, Integer>();

	public HashMap<Integer, Integer> getDailyUsageMap() {
		return dailyUsageMap;
	}

	public void setDailyUsageMap(HashMap<Integer, Integer> dailyUsageMap) {
		this.dailyUsageMap = dailyUsageMap;
	}
	

}
