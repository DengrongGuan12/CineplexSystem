package edu.nju.cineplex.factory;

import edu.nju.cineplex.dao.AccountDao;
import edu.nju.cineplex.dao.ActivityDao;
import edu.nju.cineplex.dao.AdminDao;
import edu.nju.cineplex.dao.ChoiceDao;
import edu.nju.cineplex.dao.HallDao;
import edu.nju.cineplex.dao.MemberDao;
import edu.nju.cineplex.dao.MovieDao;
import edu.nju.cineplex.dao.OptionDao;
import edu.nju.cineplex.dao.PlanDao;
import edu.nju.cineplex.dao.PurchaseDao;
import edu.nju.cineplex.dao.RechargeDao;
import edu.nju.cineplex.dao.impl.AccountDaoImpl;
import edu.nju.cineplex.dao.impl.ActivityDaoImpl;
import edu.nju.cineplex.dao.impl.AdminDaoImpl;
import edu.nju.cineplex.dao.impl.ChoiceDaoImpl;
import edu.nju.cineplex.dao.impl.HallDaoImpl;
import edu.nju.cineplex.dao.impl.MemberDaoImpl;
import edu.nju.cineplex.dao.impl.MovieDaoImpl;
import edu.nju.cineplex.dao.impl.OptionDaoImpl;
import edu.nju.cineplex.dao.impl.PlanDaoImpl;
import edu.nju.cineplex.dao.impl.PurchaseDaoImpl;
import edu.nju.cineplex.dao.impl.RechargeDaoImpl;

public class DaoFactory {
	public static MemberDao getMemberDao(){
		return MemberDaoImpl.getInstance();
	}
	public static AdminDao getAdminDao(){
		return AdminDaoImpl.getInstance();
	}
	public static MovieDao getMovieDao(){
		return MovieDaoImpl.getInstance();
	}
	public static PlanDao getPlanDao(){
		return PlanDaoImpl.getInstance();
	}
	public static HallDao getHallDao(){
		return HallDaoImpl.getInstance();
	}
	public static PurchaseDao getPurchaseDao(){
		return PurchaseDaoImpl.getInstance();
	}
	public static ActivityDao getActivityDao(){
		return ActivityDaoImpl.getInstance();
	}
	public static OptionDao getOptionDao(){
		return OptionDaoImpl.getInstance();
	}
	public static AccountDao getAccountDao(){
		return AccountDaoImpl.getInstance();
	}
	public static ChoiceDao getChoiceDao(){
		return ChoiceDaoImpl.getInstance();
	}
	public static RechargeDao getRechargeDao(){
		return RechargeDaoImpl.getInstance();
	}

}
