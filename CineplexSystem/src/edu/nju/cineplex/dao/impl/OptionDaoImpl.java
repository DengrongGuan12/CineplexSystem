package edu.nju.cineplex.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.dao.OptionDao;
import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.Option;
import edu.nju.cineplex.model.Plan;
import edu.nju.common.HibernateUtil;

public class OptionDaoImpl implements OptionDao{
	private static OptionDaoImpl optionDaoImpl=new OptionDaoImpl();
	private OptionDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public static OptionDao getInstance(){
		return optionDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	@Override
	public boolean insert(Option option) {
		// TODO Auto-generated method stub
		return baseDao.save(option);
	}
	@Override
	public Option getOptionById(int id) {
		// TODO Auto-generated method stub
		return (Option)baseDao.load(Option.class, id);
	}
	@Override
	public List<Option> getOptionsByActivityId(int activityId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Option.class);
		criteria.add(Expression.eq("activityId", activityId));
		List<Option> list=(List<Option>)criteria.list();
		return list;
	}
	

}
