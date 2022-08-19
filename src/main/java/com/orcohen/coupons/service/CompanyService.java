package com.orcohen.coupons.service;

import java.util.List;

import com.orcohen.coupons.enums.infoType;
import com.orcohen.coupons.utils.LoggerSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.orcohen.coupons.repositories.*;
import com.orcohen.coupons.beans.Company;
import com.orcohen.coupons.beans.Coupon;
import com.orcohen.coupons.enums.Category;
import com.orcohen.coupons.exception.*;

@Service
@Scope("prototype")
public class CompanyService extends ServiceFacde {

	@Autowired
	private LoggerSave log;

	private int companyid;

	public CompanyService() {
		super();
	}

	public boolean login(String Email, String Password) {

		if (companyRepository.findByEmailAndPassword(Email, Password) != null) {
			Company CompenyFromdb = companyRepository.findByEmailAndPassword(Email, Password);
			companyid = CompenyFromdb.getId();
//			System.out.println("login success : " + Email);
			log.SendLogerSevere(String.format("%s : Login to The System ", Email));

			return true;
		}
		// System.out.println("User Or Password is Incoreect: " + Email);
		return false;
	}

	@Override
	public int get_id(String Email, String Password) {
		return companyRepository.findByEmailAndPassword(Email, Password).getId();
	}
	@Override
	public String get_info(String Email, String Password, infoType infoType) {
		Company company = companyRepository.findByEmailAndPassword(Email,Password);
		switch (infoType){
			case name:
				return company.getName();
			case image:
				return company.getImage();
			default:
				return null;
		}
	}

	public int loginandgetid(String Email, String Password) {
		if (companyRepository.findByEmailAndPassword(Email, Password) != null) {
			Company CompenyFromdb = companyRepository.findByEmailAndPassword(Email, Password);
			companyid = CompenyFromdb.getId();

			return companyid;
		}
		// System.out.println("User Or Password is Incoreect: " + Email);
		return 0;
	}

	public void AddCoupon(Coupon coupon) throws Exception {

		Coupon couponTitlefromdb = couponRepository.findByCompanyidAndTitle(companyid, coupon.getTitle());
		// System.out.println(couponTitlefromdb);

		if (couponTitlefromdb == null) {
			coupon.setcompanyid(companyid);
			couponRepository.save(coupon);
			System.out.println("Congratulations the New Coupon has been added to the system");

		} else {
			throw new ErorException("The coupon Title already exists in the system");
		}

	}

	public void UpdateCoupon(Coupon coupon) throws Exception {

		Coupon couponfromdb = couponRepository.findById(coupon.getId());
		if (couponfromdb != null) {
			if (coupon.getcompanyid() == couponfromdb.getcompanyid() && coupon.getId() == couponfromdb.getId()) {
				couponRepository.save(coupon);
				System.out.println("Congratulations the Coupon has been Update at the system");
			} else {
				throw new ErorException("Can't update Coupon-ID OR Company-ID ");
			}
		} else {
			throw new ErorException("The Coupon_ID Is Not Exsist");
		}

	}

	public void DeleteCoupon(int couponid) throws ErorException {

		Coupon coupondb = couponRepository.findById(couponid);

		if (coupondb != null) {

			couponRepository.deleteById(couponid);

			if (couponRepository.findById(couponid) == null) {
				System.out.println("The Coupon ID " + couponid + " IS Delete !");
			}
		} else {
			throw new ErorException("The Coupon With That id Is Not Exsist");
		}

	}

	public List<Coupon> GetcompanyCouponsByCompenyID(int id) {

		if (companyid != id) {
			companyid = id;
		}

		return couponRepository.findByCompanyid(companyid);

	}

	public List<Coupon> GetcompanyCouponsByIDAndCategory(int id, Category category) {

		if (companyid == 0) {
			companyid = id;
		}

		return couponRepository.findAllByCompanyidAndCategory(companyid, category);

	}

	public List<Coupon> GetcompanyCouponsByMaxprice(int id, double Maxprice) {

		if (companyid == 0) {
			companyid = id;
		}
		return couponRepository.findByCompanyidAndPriceLessThanEqual(companyid, Maxprice);
	}

	public Company GetcompanyDetails(int id) {
		if (companyid == 0) {
			companyid = id;
		}
		return companyRepository.findById(companyid);
	}

}
