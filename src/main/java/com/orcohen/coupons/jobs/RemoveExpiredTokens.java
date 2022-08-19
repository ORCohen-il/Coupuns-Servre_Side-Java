package com.orcohen.coupons.jobs;

import com.orcohen.coupons.utils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveExpiredTokens {

    @Autowired
    private TokenManager tokenManager;

    @Scheduled(fixedRate = 1000*10)
    public void run(){
//        System.out.println("remove expired tokens running...");
        tokenManager.removeExpired();
    }



}
