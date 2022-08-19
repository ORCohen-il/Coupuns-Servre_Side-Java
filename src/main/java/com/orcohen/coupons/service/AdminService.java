package com.orcohen.coupons.service;

import java.util.List;
import java.util.Optional;

import com.orcohen.coupons.enums.infoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.orcohen.coupons.utils.LoggerSave;
import com.orcohen.coupons.exception.*;

import com.orcohen.coupons.beans.Company;
import com.orcohen.coupons.beans.Coupon;
import com.orcohen.coupons.beans.Customer;
import com.orcohen.coupons.enums.Category;

@Service
@Scope("prototype")
public class AdminService extends ServiceFacde {

	@Autowired
	private LoggerSave log;

	public AdminService() {
		super();
	}

	public boolean login(String Email, String Password) {

		if (Email.equals("admin@admin.com") && Password.equals("admin")) {
//			System.out.println("login success : " + Email);
			log.SendLogerSevere(String.format("%s : Login to The System ", Email));

			return true;
		}
		return false;
	}

	@Override
	public int get_id(String Email, String Password) {
		return 907329496;
	}

	@Override
	public String get_info(String Email, String Password, infoType infotype) {
		switch (infotype){
			case name:
				return "User-Admin";
			case image:
				return null;
			default:
				return "User-Admin";
		}
	}

	public void AddCompany(Company company) throws Exception {

		if(companyRepository.findByEmail(company.getEmail().trim()) == null){
		if (companyRepository.findByNameAndEmail(company.getName(), company.getEmail()) == null && company.getId() == 0 ) {
			companyRepository.save(company);
			System.out.println("Congratulations the New company has been added to the system");
			log.SendLogerInfo(company.getName() + " : IS Add To The DB");
		} else {
			throw new ErorException("The company name OR Email already exists in the system");
		}
	}else{
			throw new ErorException("Email already exists in the system");
		}
	}

	public void UpdateCompany(Company company) throws Exception {

		Company companyfromdb = companyRepository.findById(company.getId());

		if(companyRepository.findByEmail(company.getEmail().trim()) == null){
			if (company.getName().equals(companyfromdb.getName())) {
			companyRepository.save(company);
			System.out.println("Congratulations the company has been Update at the system");
			log.SendLogerInfo(company.getName() + " : IS Update At The DB");

		} else {
			throw new ErorException("Can't change company name");
		}
	}else{
			throw new ErorException("Email already exists in the system");
		}
	}

	public void DeleteCompany(int companyID) throws ErorException {
		Company Comfromdb = companyRepository.findById(companyID);

		if (Comfromdb != null) {
			companyRepository.deleteById(companyID);
			log.SendLogerSevere("company : " + companyID + " : IS Delete From The DB");

		} else {
			throw new ErorException("Cant Find company_id In db");
		}

	}

	public List<Company> Getallcompanies() {

		return companyRepository.findAll();

	}

	public Company Getonecompanies(int companyID) {

		return companyRepository.findById(companyID);
	}

	public void AddCustomer(Customer customer) throws Exception {
		if (customersRepository.findByEmail(customer.getEmail()) != null) {
			throw new ErorException("The Email already exists in the system");

		} else {
			customersRepository.save(customer);
			System.out.println("Congratulations the New Customer has been added to the system");
			log.SendLogerInfo(customer.getFirstName() + " : IS Add To The DB");

		}
	}

	public void UpdateCustomer(Customer customer) throws Exception {

		if (customersRepository.findById(customer.getId()) != null) {
			customersRepository.save(customer);
			System.out.println("Customer details updated successfully");
			log.SendLogerInfo(customer.getFirstName() + " : IS updated In The DB");

		} else {
			throw new ErorException("Cant Update - Customer_ID is not Exsist");
		}

	}

	public void DeleteCustomer(int customerID) {

		Optional<Customer> cusfromdb = Optional.ofNullable(customersRepository.findById(customerID));

		if (cusfromdb != null) {
			customersRepository.deleteById(customerID);
			System.out.println("The customer ID " + customerID + " IS Delete !");
			log.SendLogerInfo("customer ID : " + customerID + " : IS Delete From The DB");
		}

	}

	public List<Customer> GetAllCustomers() {

		return customersRepository.findAll();

	}

	public List<Coupon> GetAllCoupons() {

		return couponRepository.findAll();

	}
	
	public Customer GetOneCustomer(int customerID) {
		return customersRepository.findById(customerID);
	}

	public void deleteExpiredCoupon() {

		List<Coupon> couponsfromdb = couponRepository.findAllBydate_end();

		if (couponsfromdb != null) {
			couponRepository.deleteAll(couponsfromdb);
			couponsfromdb
					.forEach((coupon) -> log.SendLogerInfo("coupon : " + coupon.getId() + " : IS Delete From The DB"));
		}

	}

	public void saveCategory(List<Category> categoryList) {

		List<?> categoryLidb = couponRepository.findAllcategories();

		categoryList.forEach((category) -> {
			if (!categoryLidb.contains(category.toString()) && (category.toString() != "Nan")) {
				couponRepository.saveCategory(category.toString());
			}
		});
	}
}
