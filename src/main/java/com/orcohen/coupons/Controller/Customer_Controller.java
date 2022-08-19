package com.orcohen.coupons.Controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.orcohen.coupons.beans.ResponseDto;
import com.orcohen.coupons.enums.ClientType;
import com.orcohen.coupons.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcohen.coupons.beans.Company;
import com.orcohen.coupons.beans.Coupon;
import com.orcohen.coupons.beans.Customer;
import com.orcohen.coupons.enums.Category;
import com.orcohen.coupons.service.AdminService;
import com.orcohen.coupons.service.CompanyService;
import com.orcohen.coupons.service.CustomerService;
import com.orcohen.coupons.utils.LoginManeger;

/**
 * Controller web service
 */
@RestController
@RequestMapping("/coupons/customer")
public class Customer_Controller {

    @Autowired
    public CustomerService customerService;

    @Autowired
    public LoginManeger loginManeger;

    private int loginid;

//    @Async
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody ControllerLogin controllerLogin) throws AuthorizationException, ExecutionException, InterruptedException {
//
//        String email = controllerLogin.getEmail();
//        String password = controllerLogin.getPassword();
//
//        ResponseDto Login_Parameter = loginManeger.Login(email, password, ClientType.CUSTOMER);
//
//        if(Login_Parameter.isSuccess()){
//            return new ResponseEntity<>(Login_Parameter, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(Login_Parameter, HttpStatus.FORBIDDEN);
//
//    }

    @PostMapping("/purchaseCoupon/{id}")
    public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon,@PathVariable("id") int cus_id) throws Exception {

        try {
            customerService.purchaseCoupon(coupon,cus_id);
            return new ResponseEntity<>("Congratulations The Coupon " + coupon.getId() + " is purchase successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }


    }

    @GetMapping("/getCustomerCoupons/{login_id}")
    public ResponseEntity<?> getCustomerCoupons(@PathVariable int login_id) {

        return new ResponseEntity<>(customerService.getCustomerCouponByCustomerID(login_id), HttpStatus.OK);

    }

    @PostMapping("/getCustomerCouponByCategory/{category}")
    public ResponseEntity<?> getCustomerCouponByCategory(@PathVariable Category category) {

        return new ResponseEntity<>(customerService.getCustomerCouponByCategory(category),
                HttpStatus.OK);
    }

    @GetMapping("/getCustomerCouponByMaxprice/{maxprice}")
    public ResponseEntity<?> getCustomerCouponByMaxprice(@PathVariable double maxprice) {

        return new ResponseEntity<>(customerService.getCustomerCouponByMaxprice(maxprice), HttpStatus.OK);
    }

    @GetMapping("/getCustomerDetails/{id}")
    public ResponseEntity<?> getCustomerDetails(@PathVariable("id") int id) {

        return new ResponseEntity<>(customerService.getCustomerDetails(id), HttpStatus.OK);
    }

}
