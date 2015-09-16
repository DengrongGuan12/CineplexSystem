package edu.nju.cineplex.action.business;

import java.util.HashMap;

public class MemberStatisticsDataBean {
	private HashMap<String, String> ageData=new HashMap<String, String>();
	private HashMap<String, String> sexDataHashMap=new HashMap<String, String>();
	private HashMap<String, String> home_data=new HashMap<String, String>();
	private HashMap<String, String> cardState_data=new HashMap<String, String>();
	

	public HashMap<String, String> getCardState_data() {
		return cardState_data;
	}

	public void setCardState_data(HashMap<String, String> cardState_data) {
		this.cardState_data = cardState_data;
	}

	public HashMap<String, String> getHome_data() {
		return home_data;
	}

	public void setHome_data(HashMap<String, String> home_data) {
		this.home_data = home_data;
	}

	public HashMap<String, String> getSexDataHashMap() {
		return sexDataHashMap;
	}

	public void setSexDataHashMap(HashMap<String, String> sexDataHashMap) {
		this.sexDataHashMap = sexDataHashMap;
	}

	public HashMap<String, String> getAgeData() {
		return ageData;
	}

	public void setAgeData(HashMap<String, String> ageData) {
		this.ageData = ageData;
	}
	

}
