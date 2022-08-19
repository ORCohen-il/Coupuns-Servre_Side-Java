package com.orcohen.coupons.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "image")
    private String image;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "coupons", joinColumns = @JoinColumn(name = "companyid"), inverseJoinColumns = @JoinColumn(name = "id"))
    List<Coupon> coupon = new ArrayList<Coupon>();

    private Company() {
    }

    public String getImage() {
        return image;
    }

    private Company(CompanyBuilder companyBuilder) {
        this.id = companyBuilder.id;
        this.name = companyBuilder.name;
        this.email = companyBuilder.email;
        this.password = companyBuilder.password;
        this.coupon = companyBuilder.coupon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Coupon> getCoupon() {
        return coupon;
    }

    public static class CompanyBuilder {

        private int id;
        private String name;
        private String email;
        private String password;
        private String image;
        private List<Coupon> coupon = new ArrayList<Coupon>();

        public CompanyBuilder() {
        }

        public CompanyBuilder setid(int id) {
            this.id = id;
            return this;
        }

        public CompanyBuilder setname(String name) {
            this.name = name;
            return this;
        }

        public CompanyBuilder setemail(String email) {
            this.email = email;
            return this;
        }

        public CompanyBuilder setpassword(String password) {
            this.password = password;
            return this;
        }

        public CompanyBuilder setimage(String image) {
            this.image = image;
            return this;
        }

        public CompanyBuilder setcoupon(List<Coupon> coupon) {
            this.coupon = coupon;
            return this;
        }

        public Company build() {
            Company companyBuilder = new Company(this);
            return companyBuilder;
        }

    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", coupon=" + coupon +
                '}';
    }
}
