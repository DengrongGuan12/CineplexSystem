package edu.nju.cineplex.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.dao.ChoiceDao;
import edu.nju.cineplex.model.Choice;
import edu.nju.cineplex.model.Plan;
import edu.nju.common.HibernateUtil;

public class ChoiceDaoImpl implements ChoiceDao{
	private static ChoiceDaoImpl choiceDaoImpl=new ChoiceDaoImpl();
	private ChoiceDaoImpl(){
		
	}
	public static ChoiceDaoImpl getInstance(){
		return choiceDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	@Override
	public List<Choice> getChoicesByMemberId(int memberId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Choice.class);
		criteria.add(Expression.eq("memberId", memberId));
		List<Choice> list=(List<Choice>)criteria.list();
		return list;
	}
	@Override
	public boolean insert(Choice choice) {
		// TODO Auto-generated method stub
		choice.setGetPiont('2');
		return baseDao.save(choice);
	}
	@Override
	public List<Choice> getChoicesByOptionId(int optionId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.currentSession();
		Criteria criteria=session.createCriteria(Choice.class);
		criteria.add(Expression.eq("optionId", optionId));
		List<Choice> list=(List<Choice>)criteria.list();
		return list;
	}
	@Override
	public void update(Choice choice) {
		// TODO Auto-generated method stub
		baseDao.update(choice);
		
	}
	

}
