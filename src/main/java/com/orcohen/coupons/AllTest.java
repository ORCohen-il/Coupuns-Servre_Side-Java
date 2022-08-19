//package com.orcohen.coupons;
//
//import java.sql.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import com.orcohen.coupons.beans.Company;
//import com.orcohen.coupons.beans.Coupon;
//import com.orcohen.coupons.beans.Customer;
//import com.orcohen.coupons.enums.Category;
//import com.orcohen.coupons.enums.ClientType;
//import com.orcohen.coupons.jobs.CoupunsExpirationDaily;
//import com.orcohen.coupons.service.AdminService;
//import com.orcohen.coupons.service.CompanyService;
//import com.orcohen.coupons.service.CustomerService;
//import com.orcohen.coupons.utils.LoginManeger;
//
//@Component
//public class AllTest {
//
//	AdminService adminFacade = null;
//	CompanyService companyFacade = null;
//	CustomerService customerFacade = null;
//
//	@Autowired
//	LoginManeger Userlogin;
//
//
//	@SuppressWarnings("deprecation")
//	Date dateStart = new java.sql.Date(121, 9, 20);
//	@SuppressWarnings("deprecation")
//	Date dateEnd = new java.sql.Date(121, 9, 30);
//
//
//	public void Test_All_Admin_Methods() throws Exception {
//
//		// -----------------------GenrateNewCampany-------------------------------------//
//
//		Company company_forAdmintest = new Company.CompanyBuilder().setemail("Ramilevy@Rm.co.il").setpassword("205080")
//				.setname("Ramilevy").build();
//
//		// -----------------------GenrateNewCustomer-------------------------------------//
//
//		Customer customer_forAdmintest = new Customer.CustomerBuilder().setemail("Admintest@00.co.il")
//				.setpassword("205080").setfirstName("Admintest").setlastName("TEstAdmin").build();
//
//		// -----------------------UpdateCampany-------------------------------------//
//
//		Company company_forAdmintest2 = new Company.CompanyBuilder().setid(14).setname("SameCom").setpassword("102030")
//				.setemail("SameCom@200.co.il").build();
//
//		// -----------------------UpdateCustomer-------------------------------------//
//
//		Customer customer_forAdmintest2 = new Customer.CustomerBuilder().setid(1).setemail("Admintest@2020.co.il")
//				.setpassword("205080").setfirstName("Admintest").setlastName("TEstAdmin").build();
//
//		// --------------------------------------------------------------------------//
//
//		adminFacade = (AdminService) Userlogin.Login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//
////		      adminFacade.AddCompany(company_forAdmintest);
////			  adminFacade.AddCustomer(customer_forAdmintest);
////			  adminFacade.UpdateCompany(company_forAdmintest2);
////			  adminFacade.UpdateCustomer(customer_forAdmintest2);
////				System.out.println("All companies - " + adminFacade.Getallcompanies());
////				System.out.println("All Customers - " + adminFacade.GetAllCustomers());
////				System.out.println(adminFacade.Getonecompanies(1));
////				System.out.println(adminFacade.GetOneCustomer(1));
////			  adminFacade.DeleteCompany(10); adminFacade.DeleteCustomer(10);
//
//	}
//
//	public void Test_All_Company_Methods() throws Exception {
//
//		// -----------------------AddNewCoupon-------------------------------------//
//
//		Coupon coupon_forcompanytest = new Coupon.CouponBuilder().setcategory(4).settitle("Vacation for everyone")
//				.setdescription("FLY TO ALL Europe For 100$").setdateStart(dateStart).setdateEnd(dateEnd)
//				.setprice(100.00).setamount(600).setimage("./image/airplane.png").build();
//
//		// -----------------------UpdateCopun-------------------------------------//
//		Coupon coupon_forcompanytest2 = new Coupon.CouponBuilder().setid(1).setcompanyid(2).setcategory(4)
//				.settitle("FLYFOR-50%").setdescription("FLY TO ALL Europe For 500$").setdateStart(dateStart)
//				.setdateEnd(dateEnd).setprice(500.00).setamount(100).setimage(null).build();
//
//		// ------------------------------------------------------------------------//
//
//		companyFacade = (CompanyService) Userlogin.Login("JAVA@JonBryce.com", "707070", ClientType.COMPANY);
//
////		  companyFacade.AddCoupon(coupon_forcompanytest);
////		  companyFacade.UpdateCoupon(coupon_forcompanytest2);
////		  System.out.println(" All Coupons is : " +companyFacade.GetcompanyCouponsByCompenyID());
////		  System.out.println(" All Coupons By Category is  : " +
////		  companyFacade.GetcompanyCouponsByIDAndCategory(0, Category.Restaurants));
////		  System.out.println("The All Coupons By MaxPrice is  : " +
////		  companyFacade.GetcompanyCouponsByMaxprice(500, 0));
////		  System.out.println("The Details Company is  : " +
////		  companyFacade.GetcompanyDetails(0));
////	      companyFacade.DeleteCoupon(10);
//
//	}
//
//	public void Test_All_Customer_Methods() throws Exception {
//
//		// -----------------------purchaseCoupon-------------------------------------//
//		Coupon coupon_forCustomertest = new Coupon.CouponBuilder().setid(10).build();
//
//		customerFacade = (CustomerService) Userlogin.Login("test@00.co.il", "205080", ClientType.CUSTOMER);
//
//     	customerFacade.purchaseCoupon(coupon_forCustomertest);
//		System.out.println("The Customer Coupon is : " + customerFacade.getCustomerCouponByCustomerID());
//		System.out.println("The Customer Coupon By Category is : " + customerFacade.getCustomerCouponByCategory(Category.FOOD));
//		System.out.println("The Customer Coupon By Maxprice is : " + customerFacade.getCustomerCouponByMaxprice(200));
//		System.out.println("The Customer Details is : " + customerFacade.getCustomerDetails());
//
//	}
//}
