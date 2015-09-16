package test;

import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberManageService memberManageService=MemberManageServiceBean.getInstance();
		memberManageService.register("aa@163.com", "1235");

	}

}
