package com.orcohen.coupons.service;

import java.util.ArrayList;
import java.util.List;

import com.orcohen.coupons.enums.infoType;
import com.orcohen.coupons.utils.LoggerSave;
import org.springframework.stereotype.Service;

import com.orcohen.coupons.beans.Coupon;

@Service
public class Custom_service extends ServiceFacde {

    @Override
    public boolean login(String Email, String Password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int get_id(String Email, String Password) {
        return 0;
    }

    @Override
    public String get_info(String Email, String Password, infoType infotype) {
        return "";
    }

    public List<Coupon> GetallCoupons() {

        return couponRepository.findAll();

    }

	public List<Coupon> GetallcategoryCoupons() {

		return (ArrayList<Coupon>) couponRepository.findAllcategories();

	}

}
