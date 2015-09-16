package edu.nju.cineplex.service;

import java.util.ArrayList;

import edu.nju.cineplex.action.business.ActivityJoinedBean;
import edu.nju.cineplex.action.business.ActivityToJoinBean;
import edu.nju.cineplex.action.business.BuyTicketResult;
import edu.nju.cineplex.action.business.MemberInfoBean;
import edu.nju.cineplex.action.business.RechargeRecordBean;
import edu.nju.cineplex.enums.MemberState;

public interface MemberManageService {
	public boolean register(String email,String passwd);
	public boolean validate(String email,String passwd);
	public double getMoneyByEmail(String email);
	public String getCardIdByEmail(String email);
	public BuyTicketResult buyTicket(String planId,int id);
	public boolean isActivated(String email);
	public String activate(String email,String cardId,String passwd,String money);
	public String addMoney(String email,String cardId,String passwd,String money);
	public MemberState getStateOfMember(String email);
	public MemberInfoBean getMemberInfoBean(String email);
	public void stop(String email);
	public void convertPoint(String email);
	public void updateInfo(String email,String age,String sex,String home);
	public ArrayList<ActivityToJoinBean> getActivityToJoinBeans(String email);
	public String chooseOption(String email,String options);
	public ArrayList<ActivityJoinedBean> getActivityJoinedBeans(String email);
	public ArrayList<RechargeRecordBean> getRechargeRecordBeans(String email);
	public String getMemberInfoByCardId(String cardId);
	public String getRechargeRecords(String email);
	public void checkState();
}
