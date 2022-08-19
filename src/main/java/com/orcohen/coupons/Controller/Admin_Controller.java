package com.orcohen.coupons.Controller;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.orcohen.coupons.beans.ResponseDto;
import com.orcohen.coupons.enums.ClientType;
import com.orcohen.coupons.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import com.orcohen.coupons.beans.Company;
import com.orcohen.coupons.beans.Customer;
import com.orcohen.coupons.service.AdminService;
import com.orcohen.coupons.service.CompanyService;
import com.orcohen.coupons.service.CustomerService;
import com.orcohen.coupons.utils.LoginManeger;

/**
 * Controller  Admin web service
 */
@RestController
@RequestMapping("/coupons/admin")
public class Admin_Controller {

	@Autowired
	public AdminService adminService;
	@Autowired
	public LoginManeger loginManeger;

//	@Async
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody ControllerLogin controllerLogin) throws AuthorizationException, ExecutionException, InterruptedException {
//
//		String email = controllerLogin.getEmail();
//		String password = controllerLogin.getPassword();
//
//		ResponseDto Login_Parameter = loginManeger.Login(email,password, ClientType.ADMINISTRATOR);
//
//		if(Login_Parameter.isSuccess()){
//			return new ResponseEntity<>(Login_Parameter, HttpStatus.OK);
//		}
//		return new ResponseEntity<>(Login_Parameter, HttpStatus.FORBIDDEN);
//
//	}

	@PostMapping("/company")
	public ResponseEntity<?> NewCompany(@RequestBody Company company) {
		/**
		 * method Work by - POST : Add new Company (json)
		 */
		if (company.getEmail() != null && company.getPassword() != null) {
			try {
				adminService.AddCompany(company);
				return new ResponseEntity<>("New Company is been Add.. The Company Id is -> " + company.getId(), HttpStatus.CREATED);

			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>("missing parameters", HttpStatus.OK);
		}
	}

	@PutMapping("/up_company")
	public ResponseEntity<?> UpdateCompany(@RequestBody Company company) {

		/**
		 * method Work by - POST : Update Company (json)
		 */

		if (company.getEmail() != null && company.getPassword() != null) {
			try {
				adminService.UpdateCompany(company);
				return new ResponseEntity<>(" Company is been Update.. Id = " + company.getId(), HttpStatus.CREATED);

			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>("missing parameters", HttpStatus.OK);
		}

	}

	@DeleteMapping("/del_company")
	public ResponseEntity<?> DeleteCompany(@RequestBody Company company) {

		/**
		 * method Work by - POST : Add new Customer (json)
		 */
//		System.out.println(company);
		int companyid = company.getId();
		if (companyid != 0) {
			try {
				adminService.DeleteCompany(companyid);
				return new ResponseEntity<>("Company " + companyid + " is Deleted From DB", HttpStatus.ACCEPTED);

			} catch (Exception e) {
				// return new ResponseEntity<>("Opps An unusual malfunction occurred",
				// HttpStatus.OK);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}

		} else {
			return new ResponseEntity<>("missing parameters", HttpStatus.OK);
		}

	}

	@GetMapping("/companies")
	public ResponseEntity<?> getAllCompanies() {
		/**
		 * method Work by - GET : Get One Customer (json)
		 */
		return new ResponseEntity<>(adminService.Getallcompanies(), HttpStatus.OK);

	}

	@GetMapping("/company/{id}")
	public ResponseEntity<?> getoneCompany(@PathVariable("id") int id) {
		/**
		 * method Work by - GET : Get One Customer (json)
		 */
		Company companyDB = adminService.Getonecompanies(id);
		if (companyDB != null) {
			return new ResponseEntity<>(companyDB, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Cant Find Company By id -> " + id, HttpStatus.OK);
		}

	}

	@PostMapping("/customer")
	public ResponseEntity<?> NewCustomer(@RequestBody Customer customer) {

		/**
		 * method Work by - POST : Add new Customer (json)
		 */

		if (customer.getEmail() != null && customer.getPassword() != null) {
			try {
				adminService.AddCustomer(customer);
				return new ResponseEntity<>("New Customer is been Add.. Id = " + customer.getId(), HttpStatus.CREATED);

			} catch (Exception e) {
				return new ResponseEntity<>("Opps An unusual malfunction occurred", HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>("missing parameters", HttpStatus.OK);
		}

	}

	@PostMapping("/up_customer")
	public ResponseEntity<?> UpdateCustomer(@RequestBody Customer customer) {

		/**
		 * method Work by - POST : Update Company (json)
		 */

		if (customer.getEmail() != null && customer.getPassword() != null) {
			try {
				adminService.UpdateCustomer(customer);
				return new ResponseEntity<>(" Customer is been Update.. Id = " + customer.getId(), HttpStatus.CREATED);

			} catch (Exception e) {
				return new ResponseEntity<>("Opps An unusual malfunction occurred", HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>("missing parameters", HttpStatus.OK);
		}

	}

	@DeleteMapping("/del_customer")
	public ResponseEntity<?> DeleteCustomer(@RequestBody Customer customer) {
		/**
		 * method Work by - POST : Add new Customer (json)
		 */

		int customerid = customer.getId();
		if (customerid != 0) {
			try {
				adminService.DeleteCustomer(customerid);
				return new ResponseEntity<>("Customer " + customerid + " is Deleted From DB", HttpStatus.ACCEPTED);

			} catch (Exception e) {
				// return new ResponseEntity<>("Opps An unusual malfunction occurred",
				// HttpStatus.OK);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>("missing parameters", HttpStatus.OK);
		}

	}

	@GetMapping("/customers")
	public ResponseEntity<?> GetAllCustomers() {
		/**
		 * method Work by - GET : Get One Customer (json)
		 */

		return new ResponseEntity<>(adminService.GetAllCustomers(), HttpStatus.OK);

	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getoneCustomer(@PathVariable("id") int id) {
		/**
		 * method Work by - GET : Get One Customer (json)
		 */

		return new ResponseEntity<>(adminService.GetOneCustomer(id), HttpStatus.OK);

	}

	// ----------------------------------------------------------

	@GetMapping("/coupons")
	public ResponseEntity<?> getAllCoupons() {

		return new ResponseEntity<>(adminService.GetAllCoupons(), HttpStatus.OK);

	}

}
