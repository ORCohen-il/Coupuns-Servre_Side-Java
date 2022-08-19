package com.orcohen.coupons.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orcohen.coupons.beans.Coupon;
import com.orcohen.coupons.enums.Category;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findByCompanyidAndTitle(int companyid, String title);

	Coupon findById(int id);

	void deleteById(int id);

	List<Coupon> findByCompanyid(int compenyID);

	List<Coupon> findAllByCompanyidAndCategory(int compenyID, Category category);

	List<Coupon> findByCompanyidAndPriceLessThanEqual(int compenyID, double maxprice);

	@Query(value = "SELECT IF( EXISTS(SELECT * FROM coupons_vs_customers WHERE id_customer =:customerId AND id_coupon =:copunId), 'TRUE', 'FALSE')", nativeQuery = true)
	String isCouponPurchaseByCustomer(@Param("customerId") int customerid, @Param("copunId") int copunid);

	@Query(value = "select * from coupons where id =:copunid and DATE_END >= CURRENT_DATE() ", nativeQuery = true)
	Coupon CouponPurchasevalid(@Param("copunid") int copunid);

	@Modifying
	@Query(value = "UPDATE coupons set amount = amount-1 where id =:copunid ", nativeQuery = true)
	@Transactional
	void SetCouponAmount(@Param("copunid") int copunid);

	@Modifying
	@Query(value = "insert into coupons_vs_customers (id_customer,id_coupon) VALUES (:cusId,:copId)", nativeQuery = true)
	@Transactional
	void addPurchaseCoupon(@Param("cusId") int cusId, @Param("copId") int copId);

	@Modifying
	@Query(value = "select * from coupons c JOIN coupons_vs_customers cc ON cc.id_coupon = c.id WHERE cc.id_customer =:cusId order by cc.id_coupon ", nativeQuery = true)
	@Transactional
	List<Coupon> findAllByCouponsBy(@Param("cusId") int customerID);

	@Modifying
	@Query(value = "select * from coupons c JOIN coupons_vs_customers cc ON cc.id_coupon = c.id WHERE cc.id_customer =:cusId AND cc.id_coupon=:category order by cc.id_coupon ", nativeQuery = true)
	@Transactional
	List<Coupon> findAllByCustomeridAndCategory(@Param("cusId") int customerID, @Param("category") int category);

	@Modifying
	@Query(value = "select * from coupons c JOIN coupons_vs_customers cc ON cc.id_coupon = c.id WHERE cc.id_customer =:cusId AND c.price <:maxprice order by cc.id_coupon ", nativeQuery = true)
	@Transactional
	List<Coupon> findAllByCustomeridAndMaxprice(@Param("cusId") int customerID, @Param("maxprice") double maxprice);

	@Query(value = "select * from coupons where date_end < CURDATE();", nativeQuery = true)
	List<Coupon> findAllBydate_end();
	
	@Query(value = "select name from categories;", nativeQuery = true)
	List<?> findAllcategories();
	
	@Modifying
	@Query(value = "insert into categories (name) value (:categoryname) ;", nativeQuery = true)
	@Transactional
	void saveCategory(@Param("categoryname") String coupon);
	
	@Modifying
	@Query(value = "DELETE FROM coupons WHERE coupons.date_end < CURDATE()", nativeQuery = true)
	void deleteByenddateBefore();

}
