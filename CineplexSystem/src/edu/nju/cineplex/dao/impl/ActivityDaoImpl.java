package edu.nju.cineplex.dao.impl;

import edu.nju.cineplex.dao.ActivityDao;
import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.model.Activity;

public class ActivityDaoImpl implements ActivityDao{

	private static ActivityDaoImpl activityDaoImpl=new ActivityDaoImpl();
	private ActivityDaoImpl(){
		
	}
	public static ActivityDaoImpl getInstance(){
		return activityDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	@Override
	public Activity getActivityById(int id) {
		// TODO Auto-generated method stub
		return (Activity)baseDao.load(Activity.class, id);
	}
	@Override
	public int insert(Activity activity) {
		// TODO Auto-generated method stub
		baseDao.save(activity);
		
		return activity.getId();
	}

}
