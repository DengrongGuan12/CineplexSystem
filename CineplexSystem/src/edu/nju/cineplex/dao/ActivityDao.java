package edu.nju.cineplex.dao;

import edu.nju.cineplex.model.Activity;

public interface ActivityDao {
	public Activity getActivityById(int id);
	public int insert(Activity activity);

}
