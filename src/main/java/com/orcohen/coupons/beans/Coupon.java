package com.orcohen.coupons.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.orcohen.coupons.enums.Category;

@Entity
@Table(name = "coupons")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int companyid;
	@Enumerated(value = EnumType.ORDINAL)
	private Category category;
	private String title;
	private String description;
	@Column(name = "DATE_START")
	private Date startDate;
	@Column(name = "DATE_END")
	private Date endDate;
	private int amount;
	private double price;
	private String image;

	public Coupon() {
	}

	public Coupon(CouponBuilder couponBuilder) {
		this.id = couponBuilder.id;
		this.companyid = couponBuilder.companyid;
		this.category = couponBuilder.category;
		this.title = couponBuilder.title;
		this.description = couponBuilder.description;
		this.startDate = couponBuilder.startDate;
		this.endDate = couponBuilder.endDate;
		this.amount = couponBuilder.amount;
		this.price = couponBuilder.price;
		this.image = couponBuilder.image;
	}

	public int getId() {
		return id;
	}

	public int getcompanyid() {
		return companyid;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getAmount() {
		return amount;
	}

	public double getprice() {
		return price;
	}

	public String getImage() {
		return image;
	}

	public void setcompanyid(int companyid) {
		this.companyid = companyid;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static class CouponBuilder {

		private int id;
		private int companyid;
		private Category category;
		private String title;
		private String description;
		private Date startDate;
		private Date endDate;
		private int amount;
		private double price;
		private String image;

		public CouponBuilder() {
		}

		public CouponBuilder setid(int id) {
			this.id = id;
			return this;
		}

		public CouponBuilder setcompanyid(int companyid) {
			this.companyid = companyid;
			return this;
		}

		public CouponBuilder setcategory(int category) {

			switch (category) {
			case 1:
				this.category = Category.FOOD;
				break;
			case 2:
				this.category = Category.Electric_product;
				break;
			case 3:
				this.category = Category.Restaurants;
				break;
			case 4:
				this.category = Category.Vacation;
				break;

			}

			return this;
		}

		public CouponBuilder settitle(String title) {
			this.title = title;
			return this;
		}

		public CouponBuilder setdescription(String description) {
			this.description = description;
			return this;
		}

		public CouponBuilder setdateStart(Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public CouponBuilder setdateEnd(Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public CouponBuilder setamount(int amount) {
			this.amount = amount;
			return this;
		}

		public CouponBuilder setprice(Double price) {
			this.price = price;
			return this;
		}

		public CouponBuilder setimage(String image) {
			this.image = image;
			return this;
		}

		public Coupon build() {
			Coupon couponBuilder = new Coupon(this);
			return couponBuilder;
		}

	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyid=" + companyid + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

}
