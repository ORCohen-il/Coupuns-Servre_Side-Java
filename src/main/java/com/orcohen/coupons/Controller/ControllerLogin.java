package com.orcohen.coupons.Controller;

import com.orcohen.coupons.enums.ClientType;

public class ControllerLogin {

	public String email;
	public String password;
	public ClientType clientType;

	public ControllerLogin() {
	}

	public ControllerLogin(String email, String password,ClientType clientType) {
		this.email = email;
		this.password = password;
		this.clientType = clientType;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public ClientType getClientType(){return clientType;}

//	public void setEmail(String email) {
//		this.email = email;
//	}

//	public void setPassword(String password) {
//		this.password = password;
//	}

}
