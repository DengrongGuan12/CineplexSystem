package edu.nju.cineplex.action.business;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MonthPurchaseNumBean {
	private HashMap<Integer, HashMap<Integer, Integer>> yearMonthHashMap=new LinkedHashMap<Integer, HashMap<Integer,Integer>>();

	public HashMap<Integer, HashMap<Integer, Integer>> getYearMonthHashMap() {
		return yearMonthHashMap;
	}

	public void setYearMonthHashMap(
			HashMap<Integer, HashMap<Integer, Integer>> yearMonthHashMap) {
		this.yearMonthHashMap = yearMonthHashMap;
	}

	
	

}
