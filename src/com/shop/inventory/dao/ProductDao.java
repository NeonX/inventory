package com.shop.inventory.dao;

import com.shop.inventory.entity.Product;

public class ProductDao extends AbstractGenericDao<Product, String> {

	public ProductDao() {
		super(ProductDao.class, Product.class);
		// TODO Auto-generated constructor stub
	}

}
