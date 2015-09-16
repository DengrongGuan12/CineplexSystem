package edu.nju.cineplex.dao.impl;

import java.util.List;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.dao.HallDao;
import edu.nju.cineplex.model.Hall;

public class HallDaoImpl implements HallDao{

	private static HallDaoImpl hallDaoImpl=new HallDaoImpl();
	private HallDaoImpl(){
		
	}
	public static HallDaoImpl getInstance(){
		return hallDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	@Override
	public List<Hall> getAllHalls() {
		// TODO Auto-generated method stub
		return baseDao.getAllList(Hall.class);
	}
	@Override
	public Hall getHallById(int id) {
		// TODO Auto-generated method stub
		
		return (Hall)baseDao.load(Hall.class, id);
	}
	

}
