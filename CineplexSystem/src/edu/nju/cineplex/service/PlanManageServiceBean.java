package edu.nju.cineplex.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import edu.nju.cineplex.action.business.PlanActivityBean;
import edu.nju.cineplex.action.business.PlanBean;
import edu.nju.cineplex.action.business.PlanDetailBean;
import edu.nju.cineplex.action.business.PlansOfDaysBean;
import edu.nju.cineplex.action.business.SimplePlanBean;
import edu.nju.cineplex.action.business.SimplePlanBeans;
import edu.nju.cineplex.action.business.TicketBean;
import edu.nju.cineplex.dao.ActivityDao;
import edu.nju.cineplex.dao.HallDao;
import edu.nju.cineplex.dao.MovieDao;
import edu.nju.cineplex.dao.PlanDao;
import edu.nju.cineplex.dao.PurchaseDao;
import edu.nju.cineplex.enums.MoviePlayState;
import edu.nju.cineplex.factory.DaoFactory;
import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.Hall;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.model.Purchase;

public class PlanManageServiceBean implements PlanManageService{

	private PlanDao planDao=DaoFactory.getPlanDao();
	private MovieDao movieDao=DaoFactory.getMovieDao();
	private HallDao hallDao=DaoFactory.getHallDao();
	private PurchaseDao purchaseDao=DaoFactory.getPurchaseDao();
	private ActivityDao activityDao=DaoFactory.getActivityDao();
	private static PlanManageServiceBean planManageServiceBean=new PlanManageServiceBean();
	private PlanManageServiceBean() {
		// TODO Auto-generated constructor stub
	}
	public static PlanManageServiceBean getInstance(){
		return planManageServiceBean;
	}
	@Override
	public String addPlan(String movieId, String startTime, String endTime,
			String hallId, String price) {
		// TODO Auto-generated method stub
		String result="";
		//先判断下线时间
		int movieIdInt=Integer.parseInt(movieId);
		Movie movie=movieDao.getMovieById(movieIdInt);
		Timestamp timestamp=movie.getEndTime();
		Timestamp tsEnd=Timestamp.valueOf(endTime);
		Timestamp tsStart=Timestamp.valueOf(startTime);
		if(timestamp.compareTo(tsEnd)<0){
			result="<response><result>"
					+ "请确保影片结束时间早于该影片的下线时间（"
					+timestamp
					+ "）"
					+ "</result></response>";
			return result;
			
		}
		List<Hall> halls=hallDao.getAllHalls();
		String hallIds="";
		ArrayList<Integer> hallIdIntegers=new ArrayList<Integer>();
		boolean exist=false;
		int hallIdInt=Integer.parseInt(hallId);
		for(Hall hall:halls){
			if(hall.getId()==hallIdInt){
				exist=true;
			}
			hallIds=hallIds+hall.getId()+" ";
			hallIdIntegers.add(hall.getId());
		}
		if(!exist){
			result="<response><result>"
					+ "该放映厅不存在！目前存在的放映厅有"
					+hallIds
					+ "号厅"
					+ "</result></response>";
			return result;
		}
		List<Plan> plans=planDao.getPlansByHallId(hallIdInt);
		boolean conflict=false;
		for(Plan plan:plans){
			Timestamp ts1=plan.getStartTime();
			Timestamp ts2=plan.getEndTime();
			if(judgeConflict(ts1, ts2, tsStart, tsEnd)){
				conflict=true;
				break;
			}
		}
		//查找该时间段内空闲的放映厅
		
		if(conflict){
			List<Plan> plans2=planDao.getAllPlans();
			for(Plan plan:plans2){
				if(judgeConflict(plan.getStartTime(), plan.getEndTime(), tsStart, tsEnd)){
					if(hallIdIntegers.contains(plan.getHallId())){
						hallIdIntegers.remove(plan.getHallId());
					}
				}
			}
			result="<response><result>"
					+ "该放映厅在该时段内已经有放映计划了！";
			if(hallIdIntegers.size()==0){
				result+="目前该时段内没有空闲的放映厅！请选择其他时段！</result></response>";
				return result;
			}else{
				result+="目前该时段内空闲的放映厅有：";
				for(Integer hallIdInteger:hallIdIntegers){
					result+=hallIdInteger+" ";
					
				}
				result+="号厅</result></response>";
			}
			return result;
		}
		Plan plan=new Plan();
		plan.setApproved('0');
		plan.setEndTime(tsEnd);
		plan.setStartTime(tsStart);
		plan.setHallId(hallIdInt);
		plan.setMovieId(movieIdInt);
		plan.setPrice(Double.valueOf(price));
		if(planDao.insert(plan)){
			result="<response><result>1</result></response>";
		}else{
			result="<response><result>出现意料之外的错误！</result></response>";
		}
		
		
		return result;
	}
	public static boolean judgeConflict(Timestamp ts1,Timestamp ts2,Timestamp tsStart,Timestamp tsEnd){
		boolean conflict=false;
		if(ts2.compareTo(tsEnd)<=0&&ts2.compareTo(tsStart)>0){
			conflict=true;
		}
		if(ts1.compareTo(tsEnd)<0&&ts1.compareTo(tsStart)>=0){
			conflict=true;
		}
		return conflict;
	}
	@Override
	public Plan getPlanById(String id) {
		// TODO Auto-generated method stub
		int idInt=Integer.parseInt(id);
		Plan plan=planDao.getPlanById(idInt);
		return plan;
	}
	@Override
	public PlanDetailBean getPlanDetailBean(String planId) {
		// TODO Auto-generated method stub
		int planIdInt=Integer.parseInt(planId);
		PlanDetailBean planDetailBean=new PlanDetailBean();
		Plan plan=planDao.getPlanById(planIdInt);
		planDetailBean.setPlan(plan);
		planDetailBean.setHall(hallDao.getHallById(plan.getHallId()));
		planDetailBean.setMovie(movieDao.getMovieById(plan.getMovieId()));
		
		return planDetailBean;
	}
	@Override
	public String examine(String planId, String result, String reason) {
		// TODO Auto-generated method stub
		int planIdInt=Integer.parseInt(planId);
		Plan plan=planDao.getPlanById(planIdInt);
		if(result.equals("approve")){
			plan.setApproved('1');
		}else {
			plan.setApproved('2');
			plan.setReason(reason);
		}
		planDao.updatePlan(plan);
		
		return "<response><result>1</result></response>";
	}
	@Override
	public PlanBean getPlanBean(String planId) {
		// TODO Auto-generated method stub
		int planIdInt=Integer.parseInt(planId);
		PlanBean planBean=new PlanBean();
		Plan plan=planDao.getPlanById(planIdInt);
		planBean.setPlan(plan);
		Timestamp startTs=plan.getStartTime();
		Timestamp endTs=plan.getEndTime();
		String start=startTs.toString();
		String date=start.split(" ")[0];
		String startTime=start.split(" ")[1];
		String startHour=startTime.split(":")[0];
		String startMin=startTime.split(":")[1];
		String end=endTs.toString();
		String endTime=end.split(" ")[1];
		String endHour=endTime.split(":")[0];
		String endMin=endTime.split(":")[1];
		planBean.setDate(date);
		planBean.setStartHour(Integer.parseInt(startHour));
		planBean.setEndHour(Integer.parseInt(endHour));
		planBean.setStartMin(Integer.parseInt(startMin));
		planBean.setEndMin(Integer.parseInt(endMin));
		return planBean;
	}
	public String changePlan(String planId,String movieId,String startTime,
			String endTime,String hallId,String price){
		String result="";
		//先判断下线时间
				int movieIdInt=Integer.parseInt(movieId);
				Movie movie=movieDao.getMovieById(movieIdInt);
				Timestamp timestamp=movie.getEndTime();
				Timestamp tsEnd=Timestamp.valueOf(endTime);
				Timestamp tsStart=Timestamp.valueOf(startTime);
				if(timestamp.compareTo(tsEnd)<0){
					result="<response><result>"
							+ "请确保影片结束时间早于该影片的下线时间（"
							+timestamp
							+ "）"
							+ "</result></response>";
					return result;
					
				}
				List<Hall> halls=hallDao.getAllHalls();
				String hallIds="";
				ArrayList<Integer> hallIdIntegers=new ArrayList<Integer>();
				boolean exist=false;
				int hallIdInt=Integer.parseInt(hallId);
				for(Hall hall:halls){
					if(hall.getId()==hallIdInt){
						exist=true;
					}
					hallIds=hallIds+hall.getId()+" ";
					hallIdIntegers.add(hall.getId());
				}
				if(!exist){
					result="<response><result>"
							+ "该放映厅不存在！目前存在的放映厅有"
							+hallIds
							+ "号厅"
							+ "</result></response>";
					return result;
				}
				List<Plan> plans=planDao.getPlansByHallId(hallIdInt);
				boolean conflict=false;
				for(Plan plan:plans){
					if(plan.getId()!=Integer.parseInt(planId)){
						Timestamp ts1=plan.getStartTime();
						Timestamp ts2=plan.getEndTime();
						if(judgeConflict(ts1, ts2, tsStart, tsEnd)){
							conflict=true;
							break;
						}
					}
					
				}
				//查找该时间段内空闲的放映厅
				
				if(conflict){
					List<Plan> plans2=planDao.getAllPlans();
					for(Plan plan:plans2){
						if(plan.getId()!=Integer.parseInt(planId)){
							if(judgeConflict(plan.getStartTime(), plan.getEndTime(), tsStart, tsEnd)){
								if(hallIdIntegers.contains(plan.getHallId())){
									hallIdIntegers.remove(plan.getHallId());
								}
							}
						}
						
					}
					result="<response><result>"
							+ "该放映厅在该时段内已经有放映计划了！";
					if(hallIdIntegers.size()==0){
						result+="目前该时段内没有空闲的放映厅！请选择其他时段！</result></response>";
						return result;
					}else{
						result+="目前该时段内空闲的放映厅有：";
						for(Integer hallIdInteger:hallIdIntegers){
							result+=hallIdInteger+" ";
							
						}
						result+="号厅</result></response>";
					}
					return result;
				}
				Plan plan=planDao.getPlanById(Integer.parseInt(planId));
				plan.setApproved('0');
				plan.setEndTime(tsEnd);
				plan.setStartTime(tsStart);
				plan.setHallId(hallIdInt);
				plan.setMovieId(movieIdInt);
				plan.setPrice(Double.valueOf(price));
				planDao.updatePlan(plan);
				result="<response><result>1</result></response>";
				return result;
	}
	@Override
	public TicketBean getTicketBean(String planId) {
		// TODO Auto-generated method stub
		int planIdInt=Integer.parseInt(planId);
		TicketBean ticketBean=new TicketBean();
		ticketBean.setPlanId(planId);
		Plan plan=planDao.getPlanById(planIdInt);
		ticketBean.setHallId(plan.getHallId());
		ticketBean.setMovieId(plan.getMovieId()+"");
		Movie movie=movieDao.getMovieById(plan.getMovieId());
		ticketBean.setMovieName(movie.getName());
		
		ticketBean.setPrice(plan.getPrice());
		ticketBean.setDescription(movie.getDescription());
		Timestamp startTs=plan.getStartTime();
		Timestamp endTs=plan.getEndTime();
		String start=startTs.toString();
		String end=endTs.toString();
		String date=start.split(" ")[0];
		String startTime=start.split(" ")[1];
		String endTime=end.split(" ")[1];
		ticketBean.setDate(date);
		ticketBean.setStartTime(startTime);
		ticketBean.setEndTime(endTime);
		Hall hall=hallDao.getHallById(plan.getHallId());
		int seatNum=hall.getSeatNum();
		String seatPlan=hall.getSeatPlan();
		String []seatPlanStrings=seatPlan.split(";");
		int []seatPlanInt=new int[seatPlanStrings.length];
		int i=0;
		boolean [][] seat=new boolean[seatPlanStrings.length][];
		for(String seatPlanString:seatPlanStrings){
			int seatNumOfRow=Integer.parseInt(seatPlanString);
			boolean[] seatPlanRow=new boolean[seatNumOfRow];
			for(int j=0;j<seatNumOfRow;j++){
				seatPlanRow[j]=true;
			}
			seat[i]=seatPlanRow;
			if(i==0){
				seatPlanInt[i]=seatNumOfRow;
			}else{
				seatPlanInt[i]=seatNumOfRow+seatPlanInt[i-1];
			}
			
			
			i++;
		}
		ticketBean.setSeatNum(seatNum);
		List<Purchase> purchases=purchaseDao.getPurchasesByPlanId(planIdInt);
		int purchaseNum=purchases.size();
		for(Purchase purchase:purchases){
			
			int seatId=purchase.getSeatId();
			int rowNum=0;
			
			while(true){
				if(seatId-seatPlanInt[rowNum]<=0){
					break;
				}
				rowNum++;
			}
			int columnNum=0;
			if(rowNum==0){
				columnNum=seatId-1;
			}else{
				columnNum=seatId-seatPlanInt[rowNum-1]-1;
			}
			seat[rowNum][columnNum]=false;//占位
			
			
			
		}
		ticketBean.setRemainderSeatNum(seatNum-purchaseNum);
		ticketBean.setSeat(seat);
		return ticketBean;
	}
	@Override
	public ArrayList<PlanActivityBean> getPlanActivityBeans() {
		// TODO Auto-generated method stub
		ArrayList<PlanActivityBean> planActivityBeans=new ArrayList<PlanActivityBean>();
		List<Plan> plans=planDao.getAllPlans();
		for(Plan plan:plans){
			if(plan.getApproved()=='1'){
				PlanActivityBean planActivityBean=new PlanActivityBean();
				planActivityBean.setPlanId(plan.getId()+"");
				int movieId=plan.getMovieId();
				Movie movie=movieDao.getMovieById(movieId);
				planActivityBean.setMovieName(movie.getName());
				planActivityBean.setMovieOfflineTime(movie.getEndTime().toString());
				Timestamp timestamp=movie.getEndTime();
				Date date=new Date();
				if(timestamp.compareTo(date)<0){
					planActivityBean.setIfOffLine(true);
				}else{
					planActivityBean.setIfOffLine(false);
				}
				if(plan.getActivityId()!=null){
					Activity activity=activityDao.getActivityById(plan.getActivityId());
					planActivityBean.setQuestion(activity.getQuestion());
				}else {
					planActivityBean.setQuestion(null);
				}
				planActivityBeans.add(planActivityBean);
			}
			
			
		}
		return planActivityBeans;
	}
	@Override
	public ArrayList<TicketBean> getTicketBeans() {
		// TODO Auto-generated method stub
		ArrayList<TicketBean> ticketBeans=new ArrayList<TicketBean>();
		List<Plan> plans=planDao.getAllPlans();
		for(Plan plan:plans){
			if(plan.getApproved()=='1'){
				TicketBean ticketBean=new TicketBean();
				ticketBean.setPlanId(plan.getId()+"");
				ticketBean.setHallId(plan.getHallId());
				Movie movie=movieDao.getMovieById(plan.getMovieId());
				ticketBean.setMovieName(movie.getName());
				ticketBean.setPrice(plan.getPrice());
				Timestamp startTs=plan.getStartTime();
				Timestamp endTs=plan.getEndTime();
				String start=startTs.toString();
				String end=endTs.toString();
				String date=start.split(" ")[0];
				String startTime=start.split(" ")[1];
				String endTime=end.split(" ")[1];
				ticketBean.setDate(date);
				ticketBean.setStartTime(startTime);
				ticketBean.setEndTime(endTime);
				Hall hall=hallDao.getHallById(plan.getHallId());
				int seatNum=hall.getSeatNum();
				ticketBean.setSeatNum(seatNum);
				List<Purchase> purchases=purchaseDao.getPurchasesByPlanId(plan.getId());
				int purchaseNum=purchases.size();
				ticketBean.setRemainderSeatNum(seatNum-purchaseNum);
				Date date2=new Date();
				if(startTs.compareTo(date2)>0){
					ticketBean.setState("尚未放映");
				}else if(endTs.compareTo(date2)>=0){
					ticketBean.setState("正在放映");
				}else{
					ticketBean.setState("放映结束");
				}
				ticketBeans.add(ticketBean);
			}
		}
		return ticketBeans;
	}
	@Override
	public double getPriceById(String planId) {
		// TODO Auto-generated method stub
		int planIdInt=Integer.parseInt(planId);
		Plan plan=planDao.getPlanById(planIdInt);
		return plan.getPrice();
	}
	@Override
	public PlansOfDaysBean getPlansOfDaysBean() {
		// TODO Auto-generated method stub
		PlansOfDaysBean plansOfDaysBean=new PlansOfDaysBean();
		Hashtable<String, ArrayList<SimplePlanBean>> day_simplePlanBeans=new Hashtable<String, ArrayList<SimplePlanBean>>();
		List<Plan> plans=planDao.getAllPlans();
		HashSet<String> movieIdSet=new HashSet<String>();
		for(Plan plan:plans){
			if(plan.getApproved()=='1'){
				movieIdSet.add(plan.getMovieId()+"");
				SimplePlanBean simplePlanBean=new SimplePlanBean();
				simplePlanBean.setPlanId(plan.getId()+"");
				simplePlanBean.setMovieId(plan.getMovieId()+"");
				simplePlanBean.setPrice(plan.getPrice()+"");
				Movie movie=movieDao.getMovieById(plan.getMovieId());
				simplePlanBean.setMovieName(movie.getName());
				Timestamp startTs=plan.getStartTime();
				Timestamp endTs=plan.getEndTime();
				String start=startTs.toString();
				String date=start.split(" ")[0];
				Date date2=new Date();
				if(startTs.compareTo(date2)>0){
					simplePlanBean.setMoviePlayState(MoviePlayState.NotPlay);
				}else if(endTs.compareTo(date2)>=0){
					simplePlanBean.setMoviePlayState(MoviePlayState.Playing);
				}else{
					simplePlanBean.setMoviePlayState(MoviePlayState.Played);
				}
				ArrayList<SimplePlanBean> simplePlanBeans=day_simplePlanBeans.get(date);
				if(simplePlanBeans==null){
					ArrayList<SimplePlanBean> simplePlanBeans2=new ArrayList<SimplePlanBean>();
					simplePlanBeans2.add(simplePlanBean);
					day_simplePlanBeans.put(date, simplePlanBeans2);
				}else{
					simplePlanBeans.add(simplePlanBean);
					day_simplePlanBeans.put(date, simplePlanBeans);
				}
			}
		}
		plansOfDaysBean.setMovieIdSet(movieIdSet);
		plansOfDaysBean.setDay_simplePlanBeans(day_simplePlanBeans);
		return plansOfDaysBean;
	}
	@Override
	public SimplePlanBeans getLikedSimplePlanBeans() {
		// TODO Auto-generated method stub
		ArrayList<SimplePlanBean> simplePlanBeans=new ArrayList<SimplePlanBean>();
		List<Plan> plans=planDao.getAllPlans();
		HashSet<String> movieIdSet=new HashSet<String>();
		int i=0;
		for(Plan plan:plans){
			if(i==4){
				break;
			}
			if(plan.getApproved()=='1'){
				Timestamp startTs=plan.getStartTime();
				Date date2=new Date();
				if(startTs.compareTo(date2)>0){
					SimplePlanBean simplePlanBean=new SimplePlanBean();
					simplePlanBean.setPrice(plan.getPrice()+"");
					simplePlanBean.setPlanId(plan.getId()+"");
					simplePlanBean.setMovieId(plan.getMovieId()+"");
					movieIdSet.add(plan.getMovieId()+"");
					Movie movie=movieDao.getMovieById(plan.getMovieId());
					simplePlanBean.setMovieName(movie.getName());
					simplePlanBeans.add(simplePlanBean);
					i++;
				}
			}
			
		}
		SimplePlanBeans simplePlanBeans2=new SimplePlanBeans();
		simplePlanBeans2.setMovieIdSet(movieIdSet);
		simplePlanBeans2.setSimplePlanBeans(simplePlanBeans);
		return simplePlanBeans2;
	}
	
	

}
