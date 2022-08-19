package com.orcohen.coupons.filters;

import com.orcohen.coupons.utils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//{"/coupons/admin/companies","/coupons/company/coupons"}
@WebFilter({"/coupons/admin/*","/coupons/company/*","/coupons/customer/*"})
public class AuthorizationFilter implements Filter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        boolean isAuthorized = tokenManager.isTokenExists(httpRequest.getHeader("authorization"));
        // System.out.println(isAuthorized);
        System.out.println(httpRequest.getHeader("authorization"));
        if (isAuthorized){
            chain.doFilter(request, response);
//            return;
        } else {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value());
        }

    }
}
