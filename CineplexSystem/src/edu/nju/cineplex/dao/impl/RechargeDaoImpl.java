package edu.nju.cineplex.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.dao.RechargeDao;
import edu.nju.cineplex.model.Recharge;
import edu.nju.common.HibernateUtil;

public class RechargeDaoImpl implements RechargeDao{

	private static RechargeDaoImpl rechargeDaoImpl=new RechargeDaoImpl();
	private RechargeDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public static RechargeDao getInstance(){
		return rechargeDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	@Override
	public boolean insert(Recharge recharge) {
		// TODO Auto-generated method stub
		return baseDao.save(recharge);
	}

	@Override
	public List<Recharge> getRechargesByMemberId(int memberId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Recharge.class);
		criteria.add(Expression.eq("memberId", memberId));
		List<Recharge> list=(List<Recharge>)criteria.list();
		return list;
	}
	

}
