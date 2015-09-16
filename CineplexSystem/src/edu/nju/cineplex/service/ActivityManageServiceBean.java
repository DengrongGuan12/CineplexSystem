package edu.nju.cineplex.service;

import java.util.ArrayList;
import java.util.List;

import edu.nju.cineplex.dao.ActivityDao;
import edu.nju.cineplex.dao.OptionDao;
import edu.nju.cineplex.dao.PlanDao;
import edu.nju.cineplex.factory.DaoFactory;
import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.Option;
import edu.nju.cineplex.model.Plan;

public class ActivityManageServiceBean implements ActivityManageService{

	private PlanDao planDao=DaoFactory.getPlanDao();
	private ActivityDao activityDao=DaoFactory.getActivityDao();
	private OptionDao optionDao=DaoFactory.getOptionDao();
	private static ActivityManageServiceBean activityManageServiceBean=new ActivityManageServiceBean();
	private ActivityManageServiceBean() {
		// TODO Auto-generated constructor stub
	}
	public static ActivityManageServiceBean getInstance(){
		return activityManageServiceBean;
	}
	@Override
	public String addActivity(String question, String optionA, String optionB,
			String optionC, String optionD, String planIds) {
		// TODO Auto-generated method stub
		String output="";
		Activity activity=new Activity();
		activity.setQuestion(question);
		int activityId=activityDao.insert(activity);
//		int activityId=9;
		Option option1=new Option();
		option1.setActivityId(activityId);
		option1.setContent(optionA);
		optionDao.insert(option1);
		Option option2=new Option();
		option2.setActivityId(activityId);
		option2.setContent(optionB);
		optionDao.insert(option2);
		if(optionC!=""){
			Option option3=new Option();
			option3.setActivityId(activityId);
			option3.setContent(optionC);
			optionDao.insert(option3);
		}
		if(optionD!=""){
			Option option4=new Option();
			option4.setActivityId(activityId);
			option4.setContent(optionD);
			optionDao.insert(option4);
		}
		String[] planIdStrings=planIds.split(";");
		ArrayList<Integer> movieIds=new ArrayList<Integer>();
		for(String planId:planIdStrings){
			int planIdInt=Integer.parseInt(planId);
			Plan plan=planDao.getPlanById(planIdInt);
			if(movieIds.contains(plan.getMovieId())){
				output="<response><result>添加失败！一次活动无法匹配多部不同的电影!</result></response>";
				return output;
			}
			
		}
		for(String planId:planIdStrings){
			int planIdInt=Integer.parseInt(planId);
			Plan plan2=planDao.getPlanById(planIdInt);
			plan2.setActivityId(activityId);
			planDao.updatePlan(plan2);
		}
		output="<response><result>添加成功！</result></response>";
		return output;
	}
	

}
