package com.orcohen.coupons.service;

import com.orcohen.coupons.enums.infoType;
import com.orcohen.coupons.utils.LoggerSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import com.orcohen.coupons.beans.Coupon;
import com.orcohen.coupons.beans.Customer;
import com.orcohen.coupons.enums.Category;
import com.orcohen.coupons.exception.*;

@Service
@Scope("prototype")
public class CustomerService extends ServiceFacde {

    private int CustomerID;
    long millis = System.currentTimeMillis();
    Date dateToday = new Date(millis);
    // private Coupon coupon;
    @Autowired
    private LoggerSave log;

    public CustomerService() {
        super();
    }

    public boolean login(String Email, String Password) {
        if (customersRepository.findByEmailAndPassword(Email, Password) != null) {
            Customer CustomerFromdb = customersRepository.findByEmailAndPassword(Email, Password);
            CustomerID = CustomerFromdb.getId();
//			System.out.println("login success : " + Email);
            log.SendLogerSevere(String.format("%s : Login to The System ", Email));

            return true;
        }
        // System.out.println("User Or Password is Incoreect: " + Email);
        return false;
    }

    @Override
    public int get_id(String Email, String Password) {
        return customersRepository.findByEmailAndPassword(Email, Password).getId();
    }

    @Override
    public String get_info(String Email, String Password, infoType infotype) {
        Customer customer = customersRepository.findByEmailAndPassword(Email, Password);
        switch (infotype) {
            case username:
                return customer.getFirstName() + " : " + customer.getLastName();
            case email:
                return customer.getEmail();
            default:
                return customer.getFirstName() + " : " + customer.getLastName();
        }

    }

    public int loginandgetid(String Email, String Password) {
        if (customersRepository.findByEmailAndPassword(Email, Password) != null) {
            Customer CustomerFromdb = customersRepository.findByEmailAndPassword(Email, Password);
            CustomerID = CustomerFromdb.getId();
            return CustomerID;
        }
        // System.out.println("User Or Password is Incoreect: " + Email);
        return 0;
    }

    public void purchaseCoupon(Coupon coupon, int cus_id) throws Exception {
        CustomerID = cus_id;
        Coupon getcouponfromdb = couponRepository.findById(coupon.getId());
        String isExsist = couponRepository.isCouponPurchaseByCustomer(CustomerID, coupon.getId());


        if (customersRepository.findById(CustomerID) == null) {
            throw new ErorException("Cant find customer_id pls Contact support");
        }

        if (getcouponfromdb != null) {
            if (isExsist.contains("FALSE")) {
                if (getcouponfromdb.getAmount() >= 1) {

                    if (couponRepository.CouponPurchasevalid(coupon.getId()) != null) {
                        couponRepository.SetCouponAmount(coupon.getId());
                        couponRepository.addPurchaseCoupon(CustomerID, coupon.getId());
                        System.out.println("Congratulations The Coupon " + coupon.getId() + " is purchase successfully");

                    } else {
                        throw new ErorException("Unfortunately can not purchase coupon " + coupon.getId() + " because the coupon Isn't Vaild");
                    }
                } else {
                    throw new ErorException("Unfortunately can not purchase coupon " + coupon.getId() + " because the amount of coupons has run out");
                }

            } else {
                throw new ErorException(
                        "Unfortunately You can't purchase coupon " + coupon.getId() + " because that coupon is already been purchased");
            }
        } else {
            throw new ErorException("Cant Find coupon " + coupon.getId() + " To purchase ");
        }

    }

    public List<Coupon> getCustomerCouponByCustomerID(int login_id) {

        return couponRepository.findAllByCouponsBy(login_id);

    }

    public List<Coupon> getCustomerCouponByCategory(Category category) {

        return couponRepository.findAllByCustomeridAndCategory(CustomerID, category.getid());

    }

    public List<Coupon> getCustomerCouponByMaxprice(double Maxprice) {


        return couponRepository.findAllByCustomeridAndMaxprice(CustomerID, Maxprice);
    }

    public Customer getCustomerDetails(int customerID) {

        return customersRepository.findById(customerID);
    }

}
