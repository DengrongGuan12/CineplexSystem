package edu.nju.cineplex.service;

import edu.nju.cineplex.action.business.DailyUsageBean;
import edu.nju.cineplex.action.business.MemberStatisticsDataBean;
import edu.nju.cineplex.action.business.MonthPurchaseNumBean;

public interface AdminManageService {
	/**
	 * @param name
	 * @param passwd
	 * @return 
	 */
	public boolean validate(String name,String passwd);
	
	public MemberStatisticsDataBean getMemberStatisticsDataBean();
	
	public MonthPurchaseNumBean getMonthPurchaseNumBean();
	
	public DailyUsageBean getDailyUsageBean(String year,String month);

}
