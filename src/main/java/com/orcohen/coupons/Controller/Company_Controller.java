package com.orcohen.coupons.Controller;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.orcohen.coupons.beans.ResponseDto;
import com.orcohen.coupons.enums.ClientType;
import com.orcohen.coupons.exception.AuthorizationException;
import com.orcohen.coupons.utils.LoginManeger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import com.orcohen.coupons.beans.Coupon;
import com.orcohen.coupons.enums.Category;
import com.orcohen.coupons.service.CompanyService;

/**
 * Controller web service
 */
@RestController
@RequestMapping("/coupons/company")
public class Company_Controller {

    @Autowired
    public CompanyService companyService;

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
//        ResponseDto Login_Parameter = loginManeger.Login(email, password, ClientType.COMPANY);
//
//        if(Login_Parameter.isSuccess()){
//            return new ResponseEntity<>(Login_Parameter, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(Login_Parameter, HttpStatus.FORBIDDEN);
//    }

    @PostMapping("/coupon")
    public ResponseEntity<?> NewCoupon(@RequestBody Coupon coupon) {
        /**
         * method Work by - POST : Add new Customer (json)
         */

        if (coupon.getCategory() != null && coupon.getStartDate() != null) {
            try {
                companyService.AddCoupon(coupon);
                return new ResponseEntity<>("New Coupon is been Add.. Id = " + coupon.getId(), HttpStatus.CREATED);

            } catch (Exception e) {
                return new ResponseEntity<>("Opps An unusual malfunction occurred", HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>("missing parameters", HttpStatus.OK);
        }

    }

    @PutMapping("/up_coupon")
    public ResponseEntity<?> UpdateCoupon(@RequestBody Coupon coupon) {
        System.out.println(coupon);
        /**
         * method Work by - POST : Update Company (json)
         */

        try {
            companyService.UpdateCoupon(coupon);
            return new ResponseEntity<>(" Company is been Update -> ID = " + coupon.getId(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Opps An unusual malfunction occurred", HttpStatus.OK);
        }

    }

    @PostMapping("/del_coupon")
    public ResponseEntity<?> DeleteCoupon(@RequestBody Coupon coupon) {

        /**
         * method Work by - POST : Add new Customer (json)
         */


        int couponid = coupon.getId();
        if (couponid != 0) {
            try {
                companyService.DeleteCoupon(couponid);
                return new ResponseEntity<>("Coupon " + couponid + " is Deleted From DB", HttpStatus.CREATED);

            } catch (Exception e) {
                // return new ResponseEntity<>("Opps An unusual malfunction occurred",
                // HttpStatus.OK);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>("missing parameters", HttpStatus.OK);
        }


    }

    @GetMapping("/company_coupon/{login_id}")
    public ResponseEntity<?> getAllCoupons(@PathVariable int login_id) {
        /**
         * method Work by - GET : Get Company Coupons (json)
         */
        return new ResponseEntity<>(companyService.GetcompanyCouponsByCompenyID(login_id), HttpStatus.OK);

    }

    @GetMapping("/{category}/{idcompany}")
    public ResponseEntity<?> getAllCouponsbyCategory(@PathVariable Category category,
                                                     @PathVariable int idcompany) {
        /**
         * method Work by - GET : Get One Customer (json)
         */

        return new ResponseEntity<>(companyService.GetcompanyCouponsByIDAndCategory(idcompany, category),
                HttpStatus.OK);
    }

    @GetMapping("/{maxprice}")
    public ResponseEntity<?> getAllCouponsByMaxprice(@PathVariable double maxprice) {

        return new ResponseEntity<>(companyService.GetcompanyCouponsByMaxprice(loginid, maxprice), HttpStatus.OK);
    }

    @GetMapping("/GetcompanyDetails/{id}")
    public ResponseEntity<?> GetcompanyDetails(@PathVariable("id") int id) {
        /**
         * method Work by - GET : Get One Customer (json)
         */

        return new ResponseEntity<>(companyService.GetcompanyDetails(id), HttpStatus.OK);
    }

}
