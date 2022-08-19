package com.orcohen.coupons.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public enum Category {

	Nan(0), FOOD(1), Electric_product(2), Restaurants(3), Vacation(4), Sport(5);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "NAME")
	private String name;
	

	private Category(int id) {
		this.id = id;
	}

	public int getid() {
		return id;
	}

}
