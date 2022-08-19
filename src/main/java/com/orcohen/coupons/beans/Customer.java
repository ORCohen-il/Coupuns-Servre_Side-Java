package com.orcohen.coupons.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "coupons_vs_customers", joinColumns = @JoinColumn(name = "ID_CUSTOMER"), inverseJoinColumns = @JoinColumn(name = "ID_COUPON"))
	private List<Coupon> coupon = new ArrayList<Coupon>();

	public Customer() {
	}

	public Customer(CustomerBuilder customerBuilder) {
		this.id = customerBuilder.id;
		this.firstName = customerBuilder.firstName;
		this.lastName = customerBuilder.lastName;
		this.email = customerBuilder.email;
		this.password = customerBuilder.password;
		this.coupon = customerBuilder.coupon;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public List<Coupon> getCoupon() {
		return coupon;
	}

	public static class CustomerBuilder {

		private int id;
		private String firstName;
		private String lastName;
		private String email;
		private String password;
		private List<Coupon> coupon = new ArrayList<Coupon>();

		public CustomerBuilder() {
		}

		public CustomerBuilder setid(int id) {
			this.id = id;
			return this;
		}

		public CustomerBuilder setfirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public CustomerBuilder setlastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public CustomerBuilder setemail(String email) {
			this.email = email;
			return this;
		}

		public CustomerBuilder setcouponlist(List<Coupon> coupon) {
			this.coupon = coupon;
			return this;
		}

		public CustomerBuilder setpassword(String password) {
			this.password = password;
			return this;
		}

		public Customer build() {
			Customer customerBuilder = new Customer(this);
			return customerBuilder;
		}

	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", coupon=" + coupon + "]";
	}

}
