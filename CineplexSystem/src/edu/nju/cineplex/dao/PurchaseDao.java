package edu.nju.cineplex.dao;

import java.util.List;

import edu.nju.cineplex.model.Purchase;

public interface PurchaseDao {
	public List<Purchase> getPurchasesByPlanId(int planId);
	public boolean insert(Purchase purchase);
	public List<Purchase> getPurchasesByMemberId(int memberId);
	
	public List<Purchase> getAllPurchases();
	

}
