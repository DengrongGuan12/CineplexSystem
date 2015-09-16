package edu.nju.cineplex.service;

import java.util.ArrayList;

import edu.nju.cineplex.action.business.PurchaseRecordBean;

public interface PurchaseManageService {
	public String insertRecord(String planId,String seatId,String memberId);
	public ArrayList<PurchaseRecordBean> getPurchaseRecordBeans(String email);
	public String getPurchaseRecordsByEmail(String email);

}
