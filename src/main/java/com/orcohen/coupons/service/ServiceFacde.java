package com.orcohen.coupons.service;

import com.orcohen.coupons.enums.infoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcohen.coupons.repositories.*;

@Service
public abstract class ServiceFacde {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CustomersRepository customersRepository;
	@Autowired
	protected CouponRepository couponRepository;

	public ServiceFacde() {
	}

	public abstract boolean login(String Email, String Password);
	public abstract int get_id(String Email, String Password);
	public abstract String get_info(String Email, String Password, infoType infotype);
}
