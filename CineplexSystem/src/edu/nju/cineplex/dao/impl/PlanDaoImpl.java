package edu.nju.cineplex.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.dao.PlanDao;
import edu.nju.cineplex.model.Plan;
import edu.nju.common.HibernateUtil;

public class PlanDaoImpl implements PlanDao{

	private static PlanDaoImpl planDaoImpl=new PlanDaoImpl();
	private PlanDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public static PlanDao getInstance(){
		return planDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	@Override
	public List<Plan> getPlansByMovieId(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Plan.class);
		criteria.add(Expression.eq("movieId", id));
		List<Plan> list=(List<Plan>)criteria.list();
		return list;
	}
	@Override
	public boolean insert(Plan plan) {
		// TODO Auto-generated method stub
		return baseDao.save(plan);
	}
	@Override
	public List<Plan> getPlansByHallId(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Plan.class);
		criteria.add(Expression.eq("hallId", id));
		List<Plan> list=(List<Plan>)criteria.list();
		return list;
	}
	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		
		return baseDao.getAllList(Plan.class);
	}
	@Override
	public Plan getPlanById(int id) {
		// TODO Auto-generated method stub
		
		return (Plan)baseDao.load(Plan.class, id);
	}
	@Override
	public void updatePlan(Plan plan) {
		// TODO Auto-generated method stub
		baseDao.update(plan);
		
	}
	@Override
	public List<Plan> getPlansByActivityId(int activityId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Plan.class);
		criteria.add(Expression.eq("activityId", activityId));
		List<Plan> list=(List<Plan>)criteria.list();
		return list;
	}
	

}
