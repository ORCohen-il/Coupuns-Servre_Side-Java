package com.orcohen.coupons.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orcohen.coupons.beans.Coupon;
import com.orcohen.coupons.beans.Customer;
import com.orcohen.coupons.enums.Category;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {

	Customer findById(int Id);

	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String Email);

	void deleteById(int id);

}
