package edu.nju.cineplex.dao;

import java.util.List;

import edu.nju.cineplex.model.Recharge;

public interface RechargeDao {
	public boolean insert(Recharge recharge);
	public List<Recharge> getRechargesByMemberId(int memberId);

}
