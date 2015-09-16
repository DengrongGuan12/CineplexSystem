package edu.nju.cineplex.dao;

import java.util.List;

import org.hibernate.Session;

public interface BaseDao {
	public Object load(Class c, String id) ;
	
	public Object load(Class c,Integer id);

	public List getAllList(Class c) ;
	
	public Long getTotalCount(Class c) ;

	public boolean save(Object bean) ;

	public void update(Object bean) ;

	public void delete(Object bean) ;
	
	public void delete(Class c, String id) ;

	public void delete(Class c, String[] ids) ;

}
