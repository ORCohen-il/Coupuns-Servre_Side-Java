package com.orcohen.coupons.filters;

import com.orcohen.coupons.exception.ErorException;

public class FilterHelper {

    public static String getApi(String url) throws Exception {
        try{
            String[] step1 = url.split("/api");
            String[] step2 = step1[1].split("/");
            return step2[1].toLowerCase();
        }
        catch (Exception e){
            throw new ErorException("");
        }
    }
}
