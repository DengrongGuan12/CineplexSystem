package edu.nju.cineplex.dao;

public interface AdminDao {
	/**
	 * @param name 用户名
	 * @param passwd 密码
	 * @return passwd
	 */
	public String getPasswdByName(String name);

}
