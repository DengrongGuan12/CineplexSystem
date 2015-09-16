package edu.nju.cineplex.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.State;

import edu.nju.cineplex.action.business.BuyTicketResult;
import edu.nju.cineplex.action.business.PurchaseRecordBean;
import edu.nju.cineplex.dao.MemberDao;
import edu.nju.cineplex.dao.MovieDao;
import edu.nju.cineplex.dao.PlanDao;
import edu.nju.cineplex.dao.PurchaseDao;
import edu.nju.cineplex.factory.DaoFactory;
import edu.nju.cineplex.model.Member;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.model.Purchase;

public class PurchaseManageServiceBean implements PurchaseManageService{

	private PurchaseDao purchaseDao=DaoFactory.getPurchaseDao();
	private MemberDao memberDao=DaoFactory.getMemberDao();
	private PlanDao planDao=DaoFactory.getPlanDao();
	private MovieDao movieDao=DaoFactory.getMovieDao();
	private static PurchaseManageServiceBean purchaseManageServiceBean=new PurchaseManageServiceBean();
	private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
	private PurchaseManageServiceBean() {
		// TODO Auto-generated constructor stub
	}
	public static PurchaseManageServiceBean getInstance(){
		return purchaseManageServiceBean;
	}
	@Override
	public String insertRecord(String planId, String seatId, String memberId) {
		// TODO Auto-generated method stub
		String result="";
		Purchase purchase=new Purchase();
		int planIdInt=Integer.parseInt(planId);
		int seatIdInt=Integer.parseInt(seatId);
		purchase.setPlanId(planIdInt);
		purchase.setSeatId(seatIdInt);
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		purchase.setTime(timestamp);
		List<Purchase> purchases=purchaseDao.getPurchasesByPlanId(planIdInt);
		for(Purchase purchase2:purchases){
			if(purchase2.getSeatId()==seatIdInt){
				result="<response><result>抱歉，你选的座位"+seatId+"刚刚被人抢先选了，请选择其他座位吧！</result></response>";
				return result;
			}
		}
		if(memberId.equals("")){
			if(!purchaseDao.insert(purchase)){
				result="<response><result>系统错误！</result></response>";
			}else{
				result="<response><result>购买成功！</result></response>";
			}
			
			
		}else{
			
			int memberIdInt=Integer.parseInt(memberId);
			BuyTicketResult buyResult=memberManageService.buyTicket(planId, memberIdInt);
			if(buyResult.getMoney().equals("-1")){
				result="<response><result>会员余额不足！</result></response>";
				return result;
			}else{
				purchase.setMemberId(memberIdInt);
				purchase.setRealMoney(buyResult.getPrice());
				if(!purchaseDao.insert(purchase)){
					result="<response><result>系统错误！</result></response>";
				}else{
					result="<response><result>购买成功！获得了"+buyResult.getPoint()+"点积分!实际支付"+buyResult.getPrice()+"元！余额为"+buyResult.getMoney()+"元</result></response>";
				}
			}
			
		}
		
		return result;
		
	}
	@Override
	public ArrayList<PurchaseRecordBean> getPurchaseRecordBeans(String email) {
		// TODO Auto-generated method stub
		Member member=memberDao.getMemberByEmail(email);
		Integer memberId=member.getId();
		List<Purchase> purchases=purchaseDao.getPurchasesByMemberId(memberId);
		ArrayList<PurchaseRecordBean> purchaseRecordBeans=new ArrayList<PurchaseRecordBean>();
	    for(Purchase purchase:purchases){
	    	int planId=purchase.getPlanId();
	    	PurchaseRecordBean purchaseRecordBean=new PurchaseRecordBean();
	    	purchaseRecordBean.setPurchaseTime(purchase.getTime()+"");
	    	purchaseRecordBean.setMoney(purchase.getRealMoney());
	    	purchaseRecordBean.setSeatId(purchase.getSeatId());
	    	Plan plan=planDao.getPlanById(planId);
	    	purchaseRecordBean.setHallId(plan.getHallId());
	    	purchaseRecordBean.setPrice(plan.getPrice());
	    	Timestamp startTs=plan.getStartTime();
	    	Timestamp endTs=plan.getEndTime();
	    	String startDateTime=startTs.toString();
	    	String playDate=startDateTime.split(" ")[0];
	    	String startTime=startDateTime.split(" ")[1];
	    	String endDateTime=endTs.toString();
	    	String endTime=endDateTime.split(" ")[1];
	    	purchaseRecordBean.setPlayDate(playDate);
	    	purchaseRecordBean.setStartTime(startTime);
	    	purchaseRecordBean.setEndTime(endTime);
	    	Movie movie=movieDao.getMovieById(plan.getMovieId());
	    	purchaseRecordBean.setMovieName(movie.getName());
	    	purchaseRecordBeans.add(purchaseRecordBean);
	    }
		return purchaseRecordBeans;
	}
	@Override
	public String getPurchaseRecordsByEmail(String email) {
		// TODO Auto-generated method stub
		String output="<response><result>";
		Member member=memberDao.getMemberByEmail(email);
		Integer memberId=member.getId();
		List<Purchase> purchases=purchaseDao.getPurchasesByMemberId(memberId);
		if(purchases.size()==0){
			output+="noRecord</result></response>";
			return output;
			
		}
		output+="hasRecords</result>";
		output+="<beans>";
		for(Purchase purchase:purchases){
			output+="<bean>";
			int planId=purchase.getPlanId();
			output+="<purchaseTime>"+purchase.getTime()+"</purchaseTime>";
			output+="<realMoney>"+purchase.getRealMoney()+"</realMoney>";
			output+="<seat>"+purchase.getSeatId()+"</seat>";
	    	Plan plan=planDao.getPlanById(planId);
	    	output+="<hallId>"+plan.getHallId()+"</hallId>";
	    	output+="<price>"+plan.getPrice()+"</price>";
	    	Timestamp startTs=plan.getStartTime();
	    	Timestamp endTs=plan.getEndTime();
	    	String startDateTime=startTs.toString();
	    	String playDate=startDateTime.split(" ")[0];
	    	String startTime=startDateTime.split(" ")[1];
	    	String endDateTime=endTs.toString();
	    	String endTime=endDateTime.split(" ")[1];
	    	output+="<playDate>"+playDate+"</playDate>";
	    	output+="<startTime>"+startTime+"</startTime>";
	    	output+="<endTime>"+endTime+"</endTime>";
	    	Movie movie=movieDao.getMovieById(plan.getMovieId());
	    	output+="<movieName>"+movie.getName()+"</movieName>";
	    	output+="</bean>";
		}
		output+="</beans>";
		output+="</response>";
		return output;
	}
	

}
