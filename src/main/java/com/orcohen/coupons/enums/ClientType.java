package com.orcohen.coupons.enums;

import javax.persistence.Entity;
import javax.persistence.Id;

public enum ClientType {

	ADMINISTRATOR(1), COMPANY(2), CUSTOMER(3);

	private final int id;

	private ClientType(int id) {
		this.id = id;
	}

	public int getid() {
		return id;
	}
}
