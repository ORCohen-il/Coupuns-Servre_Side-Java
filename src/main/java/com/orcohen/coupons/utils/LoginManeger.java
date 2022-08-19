package com.orcohen.coupons.utils;

import com.orcohen.coupons.beans.ResponseDto;
import com.orcohen.coupons.beans.TokenInfo;
import com.orcohen.coupons.enums.infoType;
import com.orcohen.coupons.repositories.TokenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.orcohen.coupons.enums.ClientType;
import com.orcohen.coupons.exception.*;
import com.orcohen.coupons.service.AdminService;
import com.orcohen.coupons.service.CompanyService;
import com.orcohen.coupons.service.CustomerService;
import com.orcohen.coupons.service.ServiceFacde;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Component
public class LoginManeger {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TokenManager tokenManager;

//	@Autowired
//	TokenRepository tokenRepository;

    ExecutorService service = Executors.newSingleThreadExecutor();

    private ResponseDto Response_Login_Massage;
    String login_name = "";
    String login_image = "";
    String token_type = "";

    public ResponseDto Login(String email, String password, ClientType clientType) throws AuthorizationException, ExecutionException, InterruptedException {
        ServiceFacde serviceFacde = null;

//		System.out.println(email + ":" + password+":" +clientType);

        if (clientType != null) {

            switch (clientType) {
                case ADMINISTRATOR:
                    serviceFacde = applicationContext.getBean(AdminService.class);
                    //md5 ADMINISTRATOR
                    token_type = "99fedb09f0f5da90e577784e5f9fdc23";
                    // serviceFacde.login(email, password);

                    break;
                case COMPANY:
                    serviceFacde = applicationContext.getBean(CompanyService.class);
                    //md5 COMPANY
                    token_type = "0f025176fb8b953690ad8f20f119ed75";
                    // serviceFacde.login(email, password);

                    break;
                case CUSTOMER:
                    serviceFacde = applicationContext.getBean(CustomerService.class);
                    //md5 CUSTOMER
                    token_type = "979ee13f032c02b4652a4e3c3928d90b";
                    // serviceFacde.login(email, password);
                    break;

            }

        } else {
            serviceFacde = null;
            System.out.println("Can't Find serviceFacde ");
        }

//		ServiceFacde finalServiceFacde = serviceFacde;
//		Future<Boolean> resultTask = service.submit(() -> {
//			boolean Valid_login = finalServiceFacde.login(email,password);
//			return Valid_login;
//		});
//		Thread.sleep(5000);
//		System.out.println(resultTask.get());


        if (serviceFacde.login(email, password)) {
            TokenInfo Token = tokenManager.generateToken(clientType);
//			byte[] TokenEncoded = Base64.encodeBase64(Token.getBytes());
            int uniq_id_login = serviceFacde.get_id(email, password);
            login_name = serviceFacde.get_info(email, password, infoType.name);
            login_image = serviceFacde.get_info(email, password, infoType.image);
            Response_Login_Massage = new ResponseDto(true, Token.getToken(), "login Successfully", uniq_id_login, Token.getDateCreated(), login_name, login_image, token_type);
        } else {
            Response_Login_Massage = new ResponseDto(false, "", "Login Failed", 0, new Date(), login_name, login_image, token_type);
        }
//		System.out.println(Response_Login_Massage.toString());

        return Response_Login_Massage;

    }

}

