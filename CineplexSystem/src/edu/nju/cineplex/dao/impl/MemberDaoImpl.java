package edu.nju.cineplex.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.dao.MemberDao;
import edu.nju.cineplex.model.Member;
import edu.nju.common.HibernateUtil;

public class MemberDaoImpl implements MemberDao{
	private static MemberDaoImpl memberDaoImpl=new MemberDaoImpl();
	private MemberDaoImpl(){
		
	}
	public static MemberDaoImpl getInstance(){
		return memberDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	public boolean insert(Member member){
		if(baseDao.save(member)){
			int id=member.getId();
			String cardId=id+"";
			while(cardId.length()<7){
				cardId="0"+cardId;
				
			}
			member.setCardId(cardId);
			baseDao.update(member);
			return true;
		}else{
			return false;
		}
		
		
	}
	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Member> members=baseDao.getAllList(Member.class);
		return members;
	}
	@Override
	public String getPasswdByEmail(String email) {
		// TODO Auto-generated method stub
		String password="";
		try {
			Session session=HibernateUtil.currentSession();
			Criteria criteria=session.createCriteria(Member.class);
			criteria.add(Expression.eq("email", email));
			List list=criteria.list();
			if(list.size()!=0){
				Member member=(Member)list.get(0);
				password= member.getPassword();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return password;
	}
	@Override
	public Member getMemberByEmail(String email) {
		// TODO Auto-generated method stub
		Member member=null;
		try {
			Session session=HibernateUtil.currentSession();
			Transaction tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(Member.class);
			criteria.add(Expression.eq("email", email));
			List list=criteria.list();
			if(list.size()!=0){
				member=(Member)list.get(0);
			}
			tx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return member;
	}
	@Override
	public Member getMemberById(int id) {
		// TODO Auto-generated method stub
		return (Member)baseDao.load(Member.class, id);
	}
	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		baseDao.update(member);
		
	}
	@Override
	public Member getMemberByCardId(String cardId) {
		// TODO Auto-generated method stub
		Member member=null;
		try {
			Session session=HibernateUtil.currentSession();
			Criteria criteria=session.createCriteria(Member.class);
			criteria.add(Expression.eq("cardId", cardId));
			List list=criteria.list();
			if(list.size()!=0){
				member=(Member)list.get(0);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return member;
	}
	

}
