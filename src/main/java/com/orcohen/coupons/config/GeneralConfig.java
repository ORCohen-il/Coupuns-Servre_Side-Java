package com.orcohen.coupons.config;

import com.orcohen.coupons.beans.TokenInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GeneralConfig {

    @Bean
    public Map<String, TokenInfo> tokens(){
        return new HashMap<>();
    }
}