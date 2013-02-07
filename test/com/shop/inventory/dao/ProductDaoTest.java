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
		
		Product p = new Product();
		p.setProductCode("P0001");
		p.setProductName("mini dress");
		
		Inventory i = new Inventory();
		i.setAvailable(10L);
		i.setBalance(15L);
		
		i.setProduct(p);
		p.setInventory(i);
		
		p = dao.merge(p);
		
		System.out.println("Inserted id : "+p.getProductCode());
	}
}
