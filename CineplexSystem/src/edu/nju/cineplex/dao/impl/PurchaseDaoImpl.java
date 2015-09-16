package edu.nju.cineplex.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.dao.PurchaseDao;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.model.Purchase;
import edu.nju.common.HibernateUtil;

public class PurchaseDaoImpl implements PurchaseDao{
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	private static PurchaseDaoImpl purchaseDaoImpl=new PurchaseDaoImpl();
	private PurchaseDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public static PurchaseDao getInstance(){
		return purchaseDaoImpl;
	}
	@Override
	public List<Purchase> getPurchasesByPlanId(int planId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Purchase.class);
		criteria.add(Expression.eq("planId", planId));
		List<Purchase> list=(List<Purchase>)criteria.list();
		return list;
	}
	@Override
	public boolean insert(Purchase purchase) {
		// TODO Auto-generated method stub
		return baseDao.save(purchase);
	}
	@Override
	public List<Purchase> getPurchasesByMemberId(int memberId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Purchase.class);
		criteria.add(Expression.eq("memberId", memberId));
		List<Purchase> list=(List<Purchase>)criteria.list();
		return list;
	}
	@Override
	public List<Purchase> getAllPurchases() {
		// TODO Auto-generated method stub
		
		return baseDao.getAllList(Purchase.class);
	}
	

}
