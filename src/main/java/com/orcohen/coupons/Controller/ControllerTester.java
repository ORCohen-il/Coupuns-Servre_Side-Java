//package com.orcohen.coupons.Controller;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import com.orcohen.store.Store.beans.Customer;
//import com.orcohen.store.Store.beans.CustomersList;
//import com.orcohen.store.Store.beans.Order;
//import com.orcohen.store.Store.beans.OrdersList;
//
//@Component
//public class ControllerTester {
//
//	private RestTemplate restTemplate;
//
//	@Autowired
//	public ControllerTester(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
//	}
//
//	private static final String SERVER_URL = "http://10.0.0.137:8080/store";
//
//	public void testApi() {
//		/**
//		 * Check method Work by - POST : Add new Customer (json)
//		 */
////		AddCustomerMethod("Moshe", 28);
//
//		// -------------------------------------------------------- //
//
//		/**
//		 * Check method Work by - GET : Get One Customer By ID (json)
//		 */
////		GetCustomerByIdMethod();
//
//		// -------------------------------------------------------- //
//
//		/**
//		 * Check method Work by - GET : Get All Customer (json)
//		 */
////		GetAllCustomersMethod();
//
//		// -------------------------------------------------------- //
//
//		/**
//		 * Check method Work by - GET : Get All Customer By fname(json)
//		 */
//
//		// GetAllCustomersByFirstNameMethod();
//
//		// -------------------------------------------------------- //
//
//		/**
//		 * Check method Work by - GET : Get All Orders (json)
//		 */
//
//		GetAllOrdersMethod();
//
//		// -------------------------------------------------------- //
//	}
//
//	private void AddCustomerMethod(String fname, int age) {
//		System.out.println("checking method AddCustomerMethod");
//		System.out.println(String.format("POST: %s/customer", SERVER_URL));
//		Customer customer = new Customer(fname, age, null);
//		ResponseEntity<String> response = restTemplate.postForEntity(String.format("%s/customer", SERVER_URL), customer,
//				String.class);
//		System.out.println("Status Code : " + response.getStatusCodeValue());
//		System.out.println(response.getBody());
//
//	}
//
//	private void GetCustomerByIdMethod() {
//		System.out.println("checking method GetCustomerById");
//		System.out.println(String.format("GET: %s/customer/id", SERVER_URL));
//		Customer customer = restTemplate.getForObject(String.format("%s/customer/36", SERVER_URL), Customer.class);
//		System.out.println(customer);
//
//	}
//
//	private void GetAllCustomersMethod() {
//		System.out.println("checking method getAllCustomers");
//		System.out.println("GET " + SERVER_URL + "/customer");
//
//		CustomersList customers = restTemplate.getForObject(String.format("%s/customer", SERVER_URL),
//				CustomersList.class);
//		customers.getCustomersList().forEach(customer -> System.out.println(customer));
//	}
//
//	private void GetAllCustomersByFirstNameMethod() {
//		System.out.println("checking method getAllCustomersByFirstName");
//		System.out.println("GET " + SERVER_URL + "/customer/byFirstName?firstName=gal");
//
//		CustomersList customers = restTemplate
//				.getForObject(String.format("%s/customer/byFirstName?firstName=OR", SERVER_URL), CustomersList.class);
//		customers.getCustomersList().forEach(customer -> System.out.println(customer));
//	}
//
//	private void GetAllOrdersMethod() {
//		System.out.println("checking method getAllOrders");
//		System.out.println("GET " + SERVER_URL + "/orders");
//
//		OrdersList orders = restTemplate.getForObject(String.format("%s/orders", SERVER_URL), OrdersList.class);
//		orders.getOrdersList().forEach(order -> System.out.println(order));
//	}
//}
