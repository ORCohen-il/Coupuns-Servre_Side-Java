package com.orcohen.coupons;

import java.sql.Date;

import com.orcohen.coupons.utils.TokenManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.orcohen.coupons.beans.Company;
import com.orcohen.coupons.beans.Coupon;
import com.orcohen.coupons.beans.Customer;
import com.orcohen.coupons.enums.ClientType;
import com.orcohen.coupons.service.AdminService;
import com.orcohen.coupons.service.CompanyService;
import com.orcohen.coupons.service.CustomerService;
import com.orcohen.coupons.utils.LoginManeger;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
//@EnableAsync
@ServletComponentScan
@EnableScheduling
@SpringBootApplication
public class MainTester {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(MainTester.class);
		// AdminService adminService = context.getBean(AdminService.class);
		LoginManeger Userlogin = context.getBean(LoginManeger.class);
		//AllTest pj = context.getBean(AllTest.class);

		System.out.println("This project Saves Logs in path : ./Log/MyLogFile.log");
		System.out.println("This project Have Controller You Can see The Documentation by going to this URL : http://localhost:8080/swagger-ui/");
		System.out.println("To Send request via Controller you mast login and get session !");
		System.out.println("To Check the Project Methods You need to uncomment the Test Method - 'pj' and the Method inside");
		
		
//		pj.Test_All_Admin_Methods();
//		pj.Test_All_Company_Methods();
//		pj.Test_All_Customer_Methods();

	}

}
