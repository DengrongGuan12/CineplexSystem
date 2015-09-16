package edu.nju.cineplex.dao;

import java.util.List;

import edu.nju.cineplex.model.Plan;

public interface PlanDao {
	public List<Plan> getPlansByMovieId(int id);
	public boolean insert(Plan plan);
	public List<Plan> getPlansByHallId(int id);
	public List<Plan> getAllPlans();
	public Plan getPlanById(int id);
	public void updatePlan(Plan plan);
	public List<Plan> getPlansByActivityId(int activityId);
}
