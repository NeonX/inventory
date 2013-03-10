	package com.shop.inventory.dao;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shop.inventory.entity.Inventory;
import com.shop.inventory.entity.Product;

public class ProductDaoTest extends TestCase {

	public void testDao(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductDao dao = (ProductDao)context.getBean("productDao");
		
		System.out.println("Inserted id : "+dao.doChkProductCodeNotExisting("P1928374"));
	}
}
