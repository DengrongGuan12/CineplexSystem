package edu.nju.cineplex.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import edu.nju.cineplex.dao.AdminDao;
import edu.nju.cineplex.model.Admin;
import edu.nju.cineplex.model.Member;
import edu.nju.common.HibernateUtil;

public class AdminDaoImpl implements AdminDao{
	private static AdminDao adminDao=new AdminDaoImpl();
	private AdminDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public static AdminDao getInstance(){
		return adminDao;
	}
	@Override
	public String getPasswdByName(String name) {
		// TODO Auto-generated method stub
		String passwd="";
		try {
			Session session=HibernateUtil.currentSession();
			Criteria criteria=session.createCriteria(Admin.class);
			criteria.add(Expression.eq("id", name));
			List list=criteria.list();
			if(list.size()!=0){
				Admin admin=(Admin)list.get(0);
				passwd= admin.getPassword();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return passwd;
	}
	

}
