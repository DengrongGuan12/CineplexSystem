package edu.nju.cineplex.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import edu.nju.cineplex.action.business.DailyUsageBean;
import edu.nju.cineplex.action.business.MemberStatisticsDataBean;
import edu.nju.cineplex.action.business.MonthPurchaseNumBean;
import edu.nju.cineplex.dao.AdminDao;
import edu.nju.cineplex.dao.MemberDao;
import edu.nju.cineplex.dao.PurchaseDao;
import edu.nju.cineplex.factory.DaoFactory;
import edu.nju.cineplex.model.Member;
import edu.nju.cineplex.model.Purchase;


public class AdminManageServiceBean implements AdminManageService{
	private PurchaseDao purchaseDao=DaoFactory.getPurchaseDao();
	private AdminDao adminDao=DaoFactory.getAdminDao();
	private MemberDao memberDao=DaoFactory.getMemberDao();
	private static AdminManageService adminManageService=new AdminManageServiceBean();
	private AdminManageServiceBean() {
		// TODO Auto-generated constructor stub
	}
	public static AdminManageService getInstance(){
		return adminManageService;
	}
	@Override
	public boolean validate(String name, String passwd) {
		// TODO Auto-generated method stub
		boolean result=false;
		if(passwd.equals(adminDao.getPasswdByName(name))){
			result=true;
		}
		return result;
	}
	@Override
	public MemberStatisticsDataBean getMemberStatisticsDataBean() {
		// TODO Auto-generated method stub
		MemberStatisticsDataBean memberStatisticsDataBean=new MemberStatisticsDataBean();
		List<Member> members=memberDao.getAllMembers();
		int total=members.size();
		int oldest=0;//最大年龄
		int youngest=200;//最小年龄
		int noage_num=0;
		List<Integer> age_list=new ArrayList<Integer>();
		int boy_num=0;
		int girl_num=0;
		int no_sex_num=0;
		HashMap<String, Integer> home_data=new HashMap<String, Integer>();
		HashMap<String, Integer> cardState=new HashMap<String, Integer>();
		for(Member member:members){
			if(member.getAge()==null){
				noage_num++;
			}else{
				int age=member.getAge();
				if(age>oldest){
					oldest=age;
				}
				if(age<youngest){
					youngest=age;
				}
				age_list.add(age);
			}
			switch (member.getSex()) {
			case '2':
				no_sex_num++;
				break;
			case '1':
				boy_num++;
				break;
			case '0':
				girl_num++;
				break;

			default:
				break;
			}
			String home=member.getHome();
			if(home==null){
				if(home_data.get("未设置")==null){
					home_data.put("未设置", 1);
				}else{
					Integer no_home_num=home_data.get("未设置");
					home_data.put("未设置", ++no_home_num);
				}
			}else{
				if(home_data.get(home)==null){
					home_data.put(home, 1);
				}else {
					Integer home_num=home_data.get(home);
					home_data.put(home, ++home_num);
				}
			}
			
			if(member.getActivated()=='0'){
				if(cardState.get("未激活")==null){
					cardState.put("未激活", 1);
				}else{
					Integer notActivated_num=cardState.get("未激活");
					cardState.put("未激活",++notActivated_num);
				}
			}else{
				switch (member.getAlive()) {
				case '1':
					if(cardState.get("有效")==null){
						cardState.put("有效", 1);
					}else{
						Integer num=cardState.get("有效");
						cardState.put("有效",++num);
					}
					
					break;
				case '0':
					if(cardState.get("停止")==null){
						cardState.put("停止", 1);
					}else{
						Integer num=cardState.get("停止");
						cardState.put("停止",++num);
					}
					break;
				case '2':
					if(cardState.get("余额不足")==null){
						cardState.put("余额不足", 1);
					}else{
						Integer num=cardState.get("余额不足");
						cardState.put("余额",++num);
					}
					break;
				case '3':
					if(cardState.get("暂停")==null){
						cardState.put("暂停", 1);
					}else{
						Integer num=cardState.get("暂停");
						cardState.put("暂停",++num);
					}
					break;
				default:
					break;
				}
			}
			
		}
		
		HashMap<String, String> age_percent=new HashMap<String, String>();
		int split=youngest;
		List<Integer> splits=new ArrayList<Integer>();
		while(split<oldest){
			splits.add(split);
			split+=5;
		}
		splits.add(oldest);
		DecimalFormat   df   =   new   DecimalFormat("#####0.0");  
		double noage_percent=noage_num/total;
		if(noage_num!=0){
			
			age_percent.put("未设置", df.format(noage_num*100/total));
		}
		
		 
		
		if(splits.size()==1){
			//同样的年龄
			double num_percent=1-noage_percent;
			age_percent.put(oldest+"", df.format(num_percent*100));
		}else{
			int num[]=new int[splits.size()-1];
			for(int i=0;i<splits.size()-1;i++){
				num[i]=0;
			}
			for(int age:age_list){
				num[this.getPosition(splits, age)]++;
			}
			for(int i=0;i<splits.size()-1;i++){
				age_percent.put(splits.get(i)+"-"+splits.get(i+1), df.format(num[i]*100/total));
			}
		}
		memberStatisticsDataBean.setAgeData(age_percent);
		
		HashMap<String, String> sex_data=new HashMap<String, String>();
		sex_data.put("男", df.format(boy_num*100/total));
		sex_data.put("女", df.format(girl_num*100/total));
		sex_data.put("未设置", df.format(no_sex_num*100/total));
		memberStatisticsDataBean.setSexDataHashMap(sex_data);
		
		HashMap<String, String> home_percent_data=new HashMap<String, String>();
		for(String home:home_data.keySet()){
			int num=home_data.get(home);
			home_percent_data.put(home, df.format(num*100/total));
		}
		
		HashMap<String, String> cardState_percent=new HashMap<String, String>();
		for(String state:cardState.keySet()){
			int num=cardState.get(state);
			cardState_percent.put(state, df.format(num*100/total));
		}
		memberStatisticsDataBean.setHome_data(home_percent_data);
		memberStatisticsDataBean.setCardState_data(cardState_percent);
		return memberStatisticsDataBean;
	}
	
	private int getPosition(List<Integer> splits,int num){
		int i=0;
		int size=splits.size();
		while(i<=size-2){
			if(i==size-2){
				return i;
			}
			if(splits.get(i)<=num&&splits.get(i+1)>num){
				return i;
			}
			i++;
		}
		return i;
		
	}
	public MonthPurchaseNumBean getMonthPurchaseNumBean(){
		MonthPurchaseNumBean monthPurchaseNumBean=new MonthPurchaseNumBean();
		List<Purchase> purchases=purchaseDao.getAllPurchases();
		HashMap<Integer, HashMap<Integer, Integer>> yearMonthHashMap=new LinkedHashMap<Integer, HashMap<Integer,Integer>>();
		for(Purchase purchase:purchases){
			Timestamp timestamp=purchase.getTime();
			int year=timestamp.getYear()+1900;
			int month=timestamp.getMonth()+1;
			HashMap<Integer, Integer> month_Num=yearMonthHashMap.get(year);
			if(month_Num==null){
				HashMap<Integer, Integer> month_num=new LinkedHashMap<Integer, Integer>();
				for(int i=1;i<=12;i++){
					if(i==month){
						month_num.put(month, 1);
						continue;
					}
					month_num.put(i, 0);
				}
				yearMonthHashMap.put(year, month_num);
			}else{
				int num=month_Num.get(month);
				month_Num.put(month, ++num);
			}
		}
		monthPurchaseNumBean.setYearMonthHashMap(yearMonthHashMap);
		return monthPurchaseNumBean;
	}
	@Override
	public DailyUsageBean getDailyUsageBean(String year, String month) {
		// TODO Auto-generated method stub
		int year_int=Integer.parseInt(year);
		int month_int=Integer.parseInt(month);
		int days=this.getDaysOfMonth(year_int, month_int);
		DailyUsageBean dailyUsageBean=new DailyUsageBean();
		dailyUsageBean.setMonth(month);
		dailyUsageBean.setYear(year);
		HashMap<Integer, Integer> dailyUsageData=new LinkedHashMap<Integer, Integer>();
		for(int i=1;i<=days;i++){
			dailyUsageData.put(i, 0);
		}
		List<Purchase> purchases=purchaseDao.getAllPurchases();
		for(Purchase purchase:purchases){
			Timestamp timestamp=purchase.getTime();
			int tmpyear=timestamp.getYear()+1900;
			int tmpmonth=timestamp.getMonth()+1;
			if(tmpyear==year_int&&tmpmonth==month_int){
				@SuppressWarnings("deprecation")
				int day=timestamp.getDate();
				int day_num=dailyUsageData.get(day);
				dailyUsageData.put(day, ++day_num);
			}
			
		}
		dailyUsageBean.setDailyUsageMap(dailyUsageData);
		return dailyUsageBean;
	}
	
	private int getDaysOfMonth(int year,int month){
		if(month==2){
			if(year%100==0){
				if(year%400==0){
					return 29;
				}else{
					return 28;
				}
			}else{
				if(year%4==0){
					return 29;
				}else{
					return 28;
				}
			}
		}
		if(month<8){
			if(month%2==0){
				return 30;
			}else{
				return 31;
			}
		}else{
			if(month%2==0){
				return 31;
				
			}else{
				return 30;
			}
		}
		
	}
	

}
