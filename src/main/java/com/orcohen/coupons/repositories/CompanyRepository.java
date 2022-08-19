package com.orcohen.coupons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orcohen.coupons.beans.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findById(int id);
	Company findByEmail(String email);
	void deleteById(int id);
	Company findByEmailAndPassword(String email, String password);
	Company findByNameAndEmail(String name, String Email);

}
