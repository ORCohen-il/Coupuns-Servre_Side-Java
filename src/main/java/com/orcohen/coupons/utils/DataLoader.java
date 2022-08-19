package com.orcohen.coupons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.orcohen.coupons.enums.Category;
import com.orcohen.coupons.service.AdminService;

@Component
public class DataLoader implements ApplicationRunner {

	@Enumerated(EnumType.STRING)
	List<Category> categoryList = Arrays.asList(Category.values());
	
	@Autowired
	protected AdminService adminService;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		adminService.saveCategory(categoryList);
	}
}
