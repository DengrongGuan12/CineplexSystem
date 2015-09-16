package edu.nju.cineplex.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;




import edu.nju.cineplex.action.business.ActivityJoinedBean;
import edu.nju.cineplex.action.business.ActivityToJoinBean;
import edu.nju.cineplex.action.business.BuyTicketResult;
import edu.nju.cineplex.action.business.MemberInfoBean;
import edu.nju.cineplex.action.business.RechargeRecordBean;
import edu.nju.cineplex.dao.AccountDao;
import edu.nju.cineplex.dao.ActivityDao;
import edu.nju.cineplex.dao.ChoiceDao;
import edu.nju.cineplex.dao.MemberDao;
import edu.nju.cineplex.dao.MovieDao;
import edu.nju.cineplex.dao.OptionDao;
import edu.nju.cineplex.dao.PlanDao;
import edu.nju.cineplex.dao.PurchaseDao;
import edu.nju.cineplex.dao.RechargeDao;
import edu.nju.cineplex.enums.MemberState;
import edu.nju.cineplex.factory.DaoFactory;
import edu.nju.cineplex.model.Account;
import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.Choice;
import edu.nju.cineplex.model.Member;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Option;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.model.Purchase;
import edu.nju.cineplex.model.Recharge;

public class MemberManageServiceBean implements MemberManageService{
	private RechargeDao rechargeDao=DaoFactory.getRechargeDao();
	private ActivityDao activityDao=DaoFactory.getActivityDao();
	private OptionDao optionDao=DaoFactory.getOptionDao();
	private ChoiceDao choiceDao=DaoFactory.getChoiceDao();
	private MovieDao movieDao=DaoFactory.getMovieDao();
	private PlanDao planDao=DaoFactory.getPlanDao();
	private MemberDao memberDao=DaoFactory.getMemberDao();
	private AccountDao accountDao=DaoFactory.getAccountDao();
	private PurchaseDao purchaseDao=DaoFactory.getPurchaseDao();
	private static MemberManageServiceBean memberManageServiceBean=new MemberManageServiceBean();
	private PlanManageService planManageService=PlanManageServiceBean.getInstance();
	private MemberManageServiceBean() {
		// TODO Auto-generated constructor stub
	}
	public static MemberManageServiceBean getInstance(){
		return memberManageServiceBean;
	}
	@Override
	public boolean register(String email, String passwd) {
		// TODO Auto-generated method stub
		List<Member> members=memberDao.getAllMembers();
		for (Member member : members) {
			if (member.getEmail().equals(email)) {
				return false;
			}
		}
		Member member=new Member();
		member.setEmail(email);
		member.setPassword(passwd);
		member.setMoney(0);
		member.setActivated('0');
		member.setLevel('0');
		member.setPoint(0);
		member.setAlive('1');
		member.setSex('2');
		return memberDao.insert(member);
	}
	@Override
	public boolean validate(String email, String passwd) {
		// TODO Auto-generated method stub
		if(passwd.equals(memberDao.getPasswdByEmail(email))){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public double getMoneyByEmail(String email) {
		// TODO Auto-generated method stub
		Member member=memberDao.getMemberByEmail(email);
		if(member!=null){
			return member.getMoney();
		}
		return 0;
	}
	@Override
	public String getCardIdByEmail(String email) {
		// TODO Auto-generated method stub
		Member member=memberDao.getMemberByEmail(email);
		if(member!=null){
			return member.getCardId();
		}
		return "";
	}
	@Override
	public BuyTicketResult buyTicket(String planId, int id) {
		// TODO Auto-generated method stub
		BuyTicketResult buyTicketResult=new BuyTicketResult();
		double price=planManageService.getPriceById(planId);
		Member member=memberDao.getMemberById(id);
		int percent=this.getPercentByLevel(member.getLevel());
		if(percent<10){
			price=price*percent/10;
		}
		double money=member.getMoney();
		if(price>money){
			buyTicketResult.setMoney("-1");
			return buyTicketResult;
		}
		buyTicketResult.setPrice(price);
		money=money-price;
		member.setMoney(money);
		if(money==0){
			member.setAlive('2');
			Date date=new Date();
			member.setNoMoneyTime(new Timestamp(date.getTime()));
		}
		int add=(int)(Math.random()*5)+1;
		int point=member.getPoint()+add;
		member.setPoint(point);
		member.setLevel(this.getLevelByMoney(money));
		memberDao.update(member);
		DecimalFormat   df   =   new   DecimalFormat("#####0.0"); 
		buyTicketResult.setMoney(df.format(money));
		buyTicketResult.setPoint(add);
		return buyTicketResult;
	}
	@Override
	public boolean isActivated(String email) {
		// TODO Auto-generated method stub
		Member member=memberDao.getMemberByEmail(email);
		if(member.getActivated()=='0'){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String activate(String email,String cardId, String passwd, String money) {
		// TODO Auto-generated method stub
		String output="";
		Account account=accountDao.getAccountById(cardId);
		if(account==null){
			output="<response><result>该银行账户不存在！请重新输入卡号！</result></response>";
			return output;
		}
		String password=account.getPassword();
		if(!password.equals(passwd)){
			output="<response><result>银行账户用户名或密码错误！请重新输入！</result></response>";
			return output;
		}
		double moneyToTransfer=Double.valueOf(money);
		double moneyInAccount=account.getMoney();
		if(moneyInAccount<moneyToTransfer){
			output="<response><result>银行账户余额不足，当前余额为"+moneyInAccount+"元</result></response>";
			return output;
		}
		double moneyRemaind=moneyInAccount-moneyToTransfer;
		account.setMoney(moneyRemaind);
		accountDao.update(account);
		this.addMoney(email, moneyToTransfer,true);
		output="<response><result>激活成功！</result></response>";
		return output;
	}
	private void addMoney(String email,double moneyToAdd,boolean recharge){
		Member member=memberDao.getMemberByEmail(email);
		double money=member.getMoney();
		member.setMoney(money+moneyToAdd);
		member.setLevel(this.getLevelByMoney(money+moneyToAdd));
		if(member.getActivated()=='0'){
			if(moneyToAdd>200){
				member.setActivated('1');
			}
		}
		if(member.getNoMoneyTime()!=null){
			member.setNoMoneyTime(null);
			member.setAlive('1');
		}
		memberDao.update(member);
		if(recharge){
			Recharge recharge2=new Recharge();
			recharge2.setMoney(moneyToAdd);
			recharge2.setMemberId(member.getId());
			Date date=new Date();
			Timestamp timestamp=new Timestamp(date.getTime());
			recharge2.setTime(timestamp);
			rechargeDao.insert(recharge2);
		}
	}
	@Override
	public String addMoney(String email, String cardId, String passwd,
			String money) {
		// TODO Auto-generated method stub
		String output="";
		output=this.activate(email, cardId, passwd, money);
		if(output.equals("<response><result>激活成功！</result></response>")){
			output="<response><result>充值成功！</result></response>";
		}
		return output;
	}
	
	private char getLevelByMoney(double money){
		char level='0';
		if(money>200&&money<=400){
			level='1';
			return level;
		}
		if(money>400&&money<=600){
			level='2';
			return level;
		}
		if(money>600){
			level='3';
			return level;
		}
		return level;
	}
	
	private int getPercentByLevel(char level){
		int percent=10;
		if(level=='1'){
			percent=9;
		}else if(level=='2'){
			percent=8;
		}else if(level=='3'){
			percent=7;
		}
		return percent;
	}
	@Override
	public MemberState getStateOfMember(String email) {
		// TODO Auto-generated method stub
		Member member=memberDao.getMemberByEmail(email);
		switch (member.getAlive()) {
		case '0':
			return MemberState.Stop;
		case '1':
			return MemberState.Normal;
		case '2':
			return MemberState.NoMoney;
		case '3':
			return MemberState.Pause;
		default:
			break;
		}
		return MemberState.Normal;
		
	}
	@Override
	public MemberInfoBean getMemberInfoBean(String email) {
		// TODO Auto-generated method stub
		MemberInfoBean memberInfoBean=new MemberInfoBean();
		Member member=memberDao.getMemberByEmail(email);
		if(member.getActivated()=='0'){
			memberInfoBean.setActivated(false);
		}else{
			memberInfoBean.setActivated(true);
		}
		if(member.getAge()==null){
			memberInfoBean.setAge("");
		}else{
			memberInfoBean.setAge(member.getAge()+"");
		}
		
		memberInfoBean.setCardNum(member.getCardId());
		memberInfoBean.setEmail(email);
		if(member.getHome()==null){
			memberInfoBean.setHome("");
		}else{
			memberInfoBean.setHome(member.getHome());
		}
		
		memberInfoBean.setLevel(member.getLevel());
		memberInfoBean.setMoney(member.getMoney());
		memberInfoBean.setPoint(member.getPoint());
		memberInfoBean.setSex(member.getSex());
		memberInfoBean.setState(this.getStateOfMember(email));
		return memberInfoBean;
	}
	@Override
	public void stop(String email) {
		// TODO Auto-generated method stub
		Member member=memberDao.getMemberByEmail(email);
		member.setAlive('0');
		memberDao.update(member);
		
	}
	@Override
	public void convertPoint(String email) {
		// TODO Auto-generated method stub
		Member member=memberDao.getMemberByEmail(email);
		int moneyToAdd=member.getPoint();
		member.setPoint(0);
		memberDao.update(member);
		this.addMoney(email, moneyToAdd,false);
		
	}
	@Override
	public void updateInfo(String email, String age, String sex, String home) {
		// TODO Auto-generated method stub
		Member member=memberDao.getMemberByEmail(email);
		member.setAge(Integer.parseInt(age));
		if(sex.equals("male")){
			member.setSex('1');
		}else{
			member.setSex('0');
		}
		member.setHome(home);
		memberDao.update(member);
		
	}
	@Override
	public ArrayList<ActivityToJoinBean> getActivityToJoinBeans(String email) {
		// TODO Auto-generated method stub
		//没有参加过且电影还未下线的活动
		ArrayList<ActivityToJoinBean> activityToJoinBeans=new ArrayList<ActivityToJoinBean>();
		Member member=memberDao.getMemberByEmail(email);
		ArrayList<Integer> activityIdsJoined=new ArrayList<Integer>();
		ArrayList<Integer> activityIdsToJoin=new ArrayList<Integer>();
		List<Choice> choices=choiceDao.getChoicesByMemberId(member.getId());
		for(Choice choice:choices){
			int optionId=choice.getOptionId();
			Option option=optionDao.getOptionById(optionId);
			Integer activityId=option.getActivityId();
			if(!activityIdsJoined.contains(activityId)){
				activityIdsJoined.add(activityId);
			}
		}
		List<Purchase> purchases=purchaseDao.getPurchasesByMemberId(member.getId());
		for(Purchase purchase:purchases){
			int planId=purchase.getPlanId();
			Plan plan=planDao.getPlanById(planId);
			int movieId=plan.getMovieId();
			Movie movie=movieDao.getMovieById(movieId);
			
			if(!MovieManageServiceBean.isOffLine(movie.getEndTime())){
				//电影尚未下线
				//获取同一部电影的所有放映计划(多个放映计划可能对应同一个活动)
				List<Plan> plans=planDao.getPlansByMovieId(movieId);
				for(Plan plan2:plans){
					Integer activityToJoinId=plan2.getActivityId();
					if(activityToJoinId!=null&&!activityIdsJoined.contains(activityToJoinId)&&!activityIdsToJoin.contains(activityToJoinId)){
						activityIdsToJoin.add(activityToJoinId);
						ActivityToJoinBean activityToJoinBean=new ActivityToJoinBean();
						Activity activity=activityDao.getActivityById(activityToJoinId);
						activityToJoinBean.setActivityId(activityToJoinId+"");
						activityToJoinBean.setQuestion(activity.getQuestion());
						HashMap<String, String> hashMap=new LinkedHashMap<String, String>();
						List<Option> options2=optionDao.getOptionsByActivityId(activityToJoinId);
						for(Option option:options2){
							hashMap.put(option.getId()+"", option.getContent());
						}
						activityToJoinBean.setHashMap(hashMap);
						activityToJoinBeans.add(activityToJoinBean);
					}
				}
				
				
			}
			
		}
		return activityToJoinBeans;
	}
	@Override
	public String chooseOption(String email, String options) {
		// TODO Auto-generated method stub
		String output="";
		String[] optionStrings=options.split(";");
		Member member=memberDao.getMemberByEmail(email);
		for(String optionString:optionStrings){
			Choice choice=new Choice();
			choice.setMemberId(member.getId());
			choice.setOptionId(Integer.parseInt(optionString));
			if(!choiceDao.insert(choice)){
				output="<response><result>系统错误！</result></response>";
				return output;
			}
		}
		output="<response><result>参与成功！请等待该电影下线时结算积分！</result></response>";
		return output;
	}
	@Override
	public ArrayList<ActivityJoinedBean> getActivityJoinedBeans(String email) {
		// TODO Auto-generated method stub
		ArrayList<ActivityJoinedBean> activityJoinedBeans=new ArrayList<ActivityJoinedBean>();
		Member member=memberDao.getMemberByEmail(email);
		List<Choice> choices=choiceDao.getChoicesByMemberId(member.getId());
		
		for(Choice choice:choices){
			ActivityJoinedBean activityJoinedBean=new ActivityJoinedBean();
			HashMap<String, Integer> optionNum=new LinkedHashMap<String, Integer>();
			Integer optionId=choice.getOptionId();
			Option option=optionDao.getOptionById(optionId);
			activityJoinedBean.setOption(option.getContent());
			Integer activityId=option.getActivityId();
			Activity activity=activityDao.getActivityById(activityId);
			activityJoinedBean.setQuestion(activity.getQuestion());
			
			List<Option> options=optionDao.getOptionsByActivityId(activityId);
			int totalJoined=0;
			for(Option option2:options){
				List<Choice> choices2=choiceDao.getChoicesByOptionId(option2.getId());
				int size=choices2.size();
				optionNum.put(option2.getContent(), size);
				totalJoined+=size;
			}
			activityJoinedBean.setOptionNum(optionNum);
			activityJoinedBean.setTotalJoined(totalJoined+"");
			//一个活动可以对应多个计划
			List<Plan> plans=planDao.getPlansByActivityId(activityId);
			int movieId=plans.get(0).getMovieId();
			Movie movie=movieDao.getMovieById(movieId);
			if(MovieManageServiceBean.isOffLine(movie.getEndTime())){
				activityJoinedBean.setEnd(true);
				if(choice.getGetPiont()=='2'){
					//没有结算
					int choiceNum=optionNum.get(option.getContent());
					boolean getPoint=true;
					for(String key:optionNum.keySet()){
						if(key.equals(option.getContent())){
							continue;
						}
						if(optionNum.get(key)>choiceNum){
							getPoint=false;
							break;
						}
					}
					if(getPoint){
						choice.setGetPiont('1');
						activityJoinedBean.setGetPoint(1);
					}else {
						choice.setGetPiont('0');
						activityJoinedBean.setGetPoint(0);
					}
					choiceDao.update(choice);
					
				}else if(choice.getGetPiont()=='1'){
					//已经结算且获得一点积分
					activityJoinedBean.setGetPoint(1);
				}else{
					//已经结算但未获得积分
					activityJoinedBean.setGetPoint(0);
				}
			}else{
				//没有结算
				activityJoinedBean.setEnd(false);
			}
			activityJoinedBeans.add(activityJoinedBean);
			
		}
		return activityJoinedBeans;
	}
	@Override
	public ArrayList<RechargeRecordBean> getRechargeRecordBeans(String email) {
		// TODO Auto-generated method stub
		ArrayList<RechargeRecordBean> rechargeRecordBeans=new ArrayList<RechargeRecordBean>();
		Member member=memberDao.getMemberByEmail(email);
		
		List<Recharge> recharges=rechargeDao.getRechargesByMemberId(member.getId());
		for(Recharge recharge:recharges){
			RechargeRecordBean rechargeRecordBean=new RechargeRecordBean();
			rechargeRecordBean.setMoney(recharge.getMoney());
			rechargeRecordBean.setTime(recharge.getTime()+"");
			rechargeRecordBeans.add(rechargeRecordBean);
		}
		return rechargeRecordBeans;
	}
	@Override
	public String getMemberInfoByCardId(String cardId) {
		// TODO Auto-generated method stub
		String output="";
		Member member=memberDao.getMemberByCardId(cardId);
		if(member==null){
			output="<response><result>notexist</result></response>";
			return output;
		}else{
			output="<response><result>exist</result>";
			output+="<cardId>"+cardId+"</cardId>";
			output+="<remainMoney>"+member.getMoney()+"元</remainMoney>";
			if (member.getActivated()=='0') {
				output+="<activated>尚未激活</activated>";
			}else{
				output+="<activated>已激活</activated>";
			}
			output+="<point>"+member.getPoint()+"</point>";
			output+="<level>"+member.getLevel()+"</level>";
			output+="<email>"+member.getEmail()+"</email>";
			if(member.getAge()==null){
				output+="<age>尚未编辑</age>";
			}else{
				output+="<age>"+member.getAge()+"</age>";
			}
			
			if(member.getSex()=='2'){
				output+="<sex>尚未编辑</sex>";
			}else if(member.getSex()=='1'){
				output+="<sex>男</sex>";
			}else{
				output+="<sex>女</sex>";
			}
			if(member.getHome()==null){
				output+="<home>尚未编辑</home>";
			}else{
				output+="<home>"+member.getHome()+"</home>";
			}
			switch (this.getStateOfMember(member.getEmail())) {
			case Normal:
				output+="<state>正常</state>";
				break;
			case NoMoney:
				output+="<state>余额不足，请及时充值！</state>";
				break;
			case Stop:
				output+="<state>帐号已停止</state>";
				break;
			case Pause:
				output+="<state>帐号暂停</state>";
				break;
			default:
				break;
			}
			output+="</response>";
		}
		return output;
	}
	@Override
	public String getRechargeRecords(String email) {
		// TODO Auto-generated method stub
		String output="<response><result>";
		Member member=memberDao.getMemberByEmail(email);
		
		List<Recharge> recharges=rechargeDao.getRechargesByMemberId(member.getId());
		if(recharges.size()==0){
			output+="noRecord</result></response>";
			return output;
		}
		output+="hasRecords</result>";
		output+="<beans>";
		for(Recharge recharge:recharges){
			output+="<bean>";
			output+="<money>"+recharge.getMoney()+"</money>";
			output+="<time>"+recharge.getTime()+"</time>";
			output+="</bean>";
		}
		output+="</beans>";
		output+="</response>";
		return output;
	}
	@Override
	public void checkState() {
		// TODO Auto-generated method stub
		List<Member> members=memberDao.getAllMembers();
		for(Member member:members){
			if(member.getAlive()!='0'){
				if(member.getNoMoneyTime()!=null){
					Date date=new Date();
					int days=(int) ((date.getTime()-member.getNoMoneyTime().getTime())/1000/60/60/24);
					if(days<356){
						member.setAlive('2');
					}else if(days<712){
						member.setAlive('3');
					}else{
						member.setAlive('0');
					}
					memberDao.update(member);
				}
				
			}
		}
		
	}
	
}
