package com.orcohen.coupons.utils;

import com.orcohen.coupons.beans.TokenInfo;
import com.orcohen.coupons.enums.ClientType;
import com.orcohen.coupons.repositories.TokenRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
// @AllArgsConstructor
@Scope("prototype")
public class TokenManager {

    @Autowired
    protected TokenRepository tokenRepository;

    public boolean isTokenExists(String token) {
        return tokenRepository.findBytoken(token) != null;
    }

    public TokenInfo generateToken(ClientType type) {


        TokenInfo info = TokenInfo.generateNewToken(type);
        tokenRepository.save(info);
        return info;
    }

    public void removeToken(String token) {
        TokenInfo tokenInfo = tokenRepository.findBytoken(token);
        tokenRepository.deleteById(tokenInfo.getId());
    }

    public void removeExpired() {
        tokenRepository.findAll().removeIf((entry) -> isTokenExpired(entry.getDateCreated()));
        List<TokenInfo> tokens = tokenRepository.findAll();
        tokens.forEach(token -> {
            if (isTokenExpired(token.getDateCreated())) {
                tokenRepository.delete(token);
//                System.out.println("Token Id : " + token.getId() + " -> His Been Expired ");
            }
        });
    }

    private boolean isTokenExpired(Date time) {
        return new Date().after(DateUtils.addMinutes(time, 30));
    }

}


//package com.orcohen.coupons.utils;
//
//        import com.orcohen.coupons.beans.TokenInfo;
//        import com.orcohen.coupons.enums.ClientType;
//        import lombok.AllArgsConstructor;
//        import org.apache.commons.lang3.time.DateUtils;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Service;
//
//        import java.util.Date;
//        import java.util.Map;
//        import java.util.UUID;

//@Service
//// @AllArgsConstructor
//public class TokenManager {
//
//    @Autowired
//    private Map<String, TokenInfo> tokens;
//
//
//    public boolean isTokenExists(String token){
//        return tokens.get(token) != null;
//    }
//
//    public String generateToken(ClientType type){
//        TokenInfo info = TokenInfo.generateNewToken(type);
//        tokens.put(info.getToken(), info);
//        return info.getToken();
//    }
//
//    public void removeToken(String token){
//        tokens.remove(token);
//    }
//
//    public void removeExpired(){
//        tokens.entrySet().removeIf((entry) ->
//                isTokenExpired(entry.getValue().getDateCreated())
//        );
//    }
//
//    private boolean isTokenExpired(Date time){
//        return new Date().after(DateUtils.addMinutes(time, 30));
//    }
//
//}












