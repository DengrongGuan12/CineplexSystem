package edu.nju.cineplex.action.business;

import java.util.HashMap;

public class ActivityJoinedBean {
	private String question;
	private String totalJoined;
	
	//选项内容：选择的人数
	private HashMap<String, Integer> optionNum=new HashMap<String, Integer>();
	
	public HashMap<String, Integer> getOptionNum() {
		return optionNum;
	}

	public void setOptionNum(HashMap<String, Integer> optionNum) {
		this.optionNum = optionNum;
	}

	private String option;//该用户选择的选项
	
	private int getPoint=0;//是否获得积分
	
	public int getGetPoint() {
		return getPoint;
	}

	public void setGetPoint(int getPoint) {
		this.getPoint = getPoint;
	}

	private boolean end=false;//有没有结算
	
	

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getTotalJoined() {
		return totalJoined;
	}

	public void setTotalJoined(String totalJoined) {
		this.totalJoined = totalJoined;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	

}
