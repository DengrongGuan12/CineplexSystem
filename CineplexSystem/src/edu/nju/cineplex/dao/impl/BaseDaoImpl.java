package edu.nju.cineplex.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.common.HibernateUtil;

public class BaseDaoImpl implements BaseDao{
	private static BaseDaoImpl baseDaoImpl=new BaseDaoImpl();
	public static BaseDaoImpl getInstance(){
		return baseDaoImpl;
	}

	/** * 根据 id 查询信息 * * @param id * @return */
	@SuppressWarnings("rawtypes")
	public Object load(Class c, String id) {
		Session session = HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		Object object=session.get(c, id);
		tx.commit();
		return object;
	}
	
	public Object load(@SuppressWarnings("rawtypes") Class c,Integer id){
		Session session=HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		Object object=session.get(c, id);
		tx.commit();
		return object;
	}

	/** * 获取所有信息 * * @param c * * @return */

	public List getAllList(Class c) {
		String hql = "from " + c.getName();
		Session session = HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		List list=session.createQuery(hql).list();
		tx.commit();
		return list;

	}

	/** * 获取总数量 * * @param c * @return */

	public Long getTotalCount(Class c) {
		Session session = HibernateUtil.currentSession();
		String hql = "select count(*) from " + c.getName();
		Long count = (Long) session.createQuery(hql).uniqueResult();
		HibernateUtil.closeSession();
		return count != null ? count.longValue() : 0;
	}

	/** * 保存 * * @param bean * */
	public boolean save(Object bean) {
		boolean suc=true;
		try {
			Session session = HibernateUtil.currentSession();
			Transaction tx=session.beginTransaction();
			session.save(bean);
			tx.commit();
			HibernateUtil.flushClearClose();
		} catch (Exception e) {
			suc=false;
			e.printStackTrace();
		}
		return suc;
	}

	/** * 更新 * * @param bean * */
	public void update(Object bean) {
		Session session = HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		session.update(bean);
		tx.commit();
		HibernateUtil.flushClearClose();
	}

	/** * 删除 * * @param bean * */
	public void delete(Object bean) {

		Session session = HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		session.delete(bean);
		tx.commit();
		HibernateUtil.flushClearClose();
	}

	/** * 根据ID删除 * * @param c 类 * * @param id ID * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String id) {
		Session session = HibernateUtil.currentSession();
		
		Object obj = session.get(c, id);
		Transaction tx=session.beginTransaction();
		session.delete(obj);
		tx.commit();
		HibernateUtil.flushClearClose();
	}

	/** * 批量删除 * * @param c 类 * * @param ids ID 集合 * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String[] ids) {
		for (String id : ids) {
			Object obj = HibernateUtil.currentSession().get(c, id);
			if (obj != null) {
				HibernateUtil.currentSession().delete(obj);
			}
		}
	}

}
