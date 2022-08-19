package com.orcohen.coupons.beans;

import org.apache.commons.lang3.time.DateUtils;

import java.util.*;

public class ResponseDto {

    private boolean success;
    private String message;
    private String Token;
    private String Token_type;
    private String login_name;
    private String login_image;
    private int Login_id;
    private Date dateExpired;

    public ResponseDto(boolean success, String Token, String message, int Login_id, Date dateCreated, String login_name, String login_image,String Token_type) {
        super();
        this.success = success;
        this.Token = Token;
        this.Token_type = Token_type;
        this.message = message;
        this.Login_id = Login_id;
        this.dateExpired = DateUtils.addMinutes(dateCreated, 120);
        this.login_name = login_name;
        this.login_image = login_image;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getToken_type() {
        return Token_type;
    }

    public void setToken_type(String token_type) {
        Token_type = token_type;
    }


    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_image() {
        return login_image;
    }

    public void setLogin_image(String login_image) {
        this.login_image = login_image;
    }

    public int getLogin_id() {
        return Login_id;
    }

    public void setLogin_id(int login_id) {
        Login_id = login_id;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", Token='" + Token + '\'' +
                ", login_name='" + login_name + '\'' +
                ", login_image='" + login_image + '\'' +
                ", Login_id=" + Login_id +
                ", dateExpired=" + dateExpired +
                '}';
    }
}
