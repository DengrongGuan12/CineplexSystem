package edu.nju.cineplex.service;

import java.util.ArrayList;

import edu.nju.cineplex.action.business.PlanActivityBean;
import edu.nju.cineplex.action.business.PlanBean;
import edu.nju.cineplex.action.business.PlanDetailBean;
import edu.nju.cineplex.action.business.PlansOfDaysBean;
import edu.nju.cineplex.action.business.SimpleMovieBean;
import edu.nju.cineplex.action.business.SimplePlanBean;
import edu.nju.cineplex.action.business.SimplePlanBeans;
import edu.nju.cineplex.action.business.TicketBean;
import edu.nju.cineplex.model.Plan;

public interface PlanManageService {
	public String addPlan(String movieId,String startTime,String endTime,String hallId,String price);
	public Plan getPlanById(String id);
	public PlanDetailBean getPlanDetailBean(String planId);
	public PlanBean getPlanBean(String planId);
	public String examine(String planId,String result,String reason);
	public String changePlan(String planId,String movieId,String startTime,String endTime,String hallId,String price);
	public TicketBean getTicketBean(String planId);
	public ArrayList<TicketBean> getTicketBeans();
	/**
	 * @return
	 * 获取所有放映计划
	 */
	public ArrayList<PlanActivityBean> getPlanActivityBeans();
	public double getPriceById(String planId);
	
	public PlansOfDaysBean getPlansOfDaysBean();
	public SimplePlanBeans getLikedSimplePlanBeans();
	

}
