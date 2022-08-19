package com.orcohen.coupons.Controller;

import com.orcohen.coupons.beans.Customer;
import com.orcohen.coupons.beans.ResponseDto;
import com.orcohen.coupons.beans.TokenInfo;
import com.orcohen.coupons.enums.ClientType;
import com.orcohen.coupons.exception.AuthorizationException;
import com.orcohen.coupons.service.AdminService;
import com.orcohen.coupons.utils.LoggerSave;
import com.orcohen.coupons.utils.LoginManeger;
import com.orcohen.coupons.utils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import com.orcohen.coupons.service.Custom_service;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/coupons/custom")
public class Custom_Controller {

    @Autowired
    public Custom_service custom_service;

    @Autowired
    AdminService adminService ;

    @Autowired
    public LoginManeger loginManeger;

    @Autowired
    private TokenManager tokenManager;

    @Async
    @GetMapping("/all_coupons")
    public ResponseEntity<?> GetallCoupons() {
        return new ResponseEntity<>(custom_service.GetallCoupons(), HttpStatus.OK);
    }

    @Async
    @GetMapping("/get_category")
    public ResponseEntity<?> get_allcategory() {
        return new ResponseEntity<>(custom_service.GetallcategoryCoupons(), HttpStatus.OK);
    }

    @PostMapping("/check_validation")
    public boolean login(@RequestBody TokenInfo token) throws AuthorizationException {

        boolean isAuthorized = tokenManager.isTokenExists(token.getToken());
//        System.out.println(token.getToken());
//        System.out.println(isAuthorized);
        return isAuthorized;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login_first(@RequestBody ControllerLogin controllerLogin) throws AuthorizationException, ExecutionException, InterruptedException {

        if (controllerLogin.getClientType() == null || controllerLogin.getEmail() == null || controllerLogin.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String email = controllerLogin.getEmail();
        String password = controllerLogin.getPassword();
        ClientType clientType = controllerLogin.getClientType();

        ResponseDto Login_Parameter = null;
        switch (clientType) {
            case ADMINISTRATOR:
                Login_Parameter = loginManeger.Login(email, password, ClientType.ADMINISTRATOR);
                break;
            case COMPANY:
                Login_Parameter = loginManeger.Login(email, password, ClientType.COMPANY);
                break;
            case CUSTOMER:
                Login_Parameter = loginManeger.Login(email, password, ClientType.CUSTOMER);
                break;
        }

        if (Login_Parameter.isSuccess()) {
            return new ResponseEntity<>(Login_Parameter, HttpStatus.OK);
        }
        return new ResponseEntity<>(Login_Parameter, HttpStatus.FORBIDDEN);
    }


    @PostMapping("/customer")
    public ResponseEntity<?> NewCustomer(@RequestBody Customer customer) {

        /**
         * method Work by - POST : Add new Customer (json)
         */

        if (customer.getEmail() != null && customer.getPassword() != null) {
            try {
                adminService.AddCustomer(customer);
                return new ResponseEntity<>("The registration was successful your Id = " + customer.getId(), HttpStatus.CREATED);

            } catch (Exception e) {
                return new ResponseEntity<>("Opps An unusual malfunction occurred Pls Contact customer service", HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>("missing parameters", HttpStatus.OK);
        }

    }
}

