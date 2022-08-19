package com.orcohen.coupons.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orcohen.coupons.repositories.CouponRepository;
import com.orcohen.coupons.service.AdminService;

@Component
public class CoupunsExpirationDaily {

	@Autowired
	private CouponRepository couponsrepo;

	public static final long Twenty_four_hours = 1000 * 60 * 60 * 24;

	public void removeExpiredDaily() {
		couponsrepo.deleteByenddateBefore();
	}
}
