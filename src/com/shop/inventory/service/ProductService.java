package com.shop.inventory.service;

import java.util.List;

import com.shop.inventory.dao.InventoryDao;
import com.shop.inventory.dao.ProductAttachDao;
import com.shop.inventory.dao.ProductDao;
import com.shop.inventory.entity.Inventory;
import com.shop.inventory.entity.Product;
import com.shop.inventory.entity.ProductAttach;

public class ProductService {

	private ProductDao productDao;
	private InventoryDao inventoryDao;
	private ProductAttachDao productAttachDao;

	public Product getProduct(String productCode){
		return productDao.getProduct(productCode);
	}
	
	public Product getProduct(String productCode, String productName){
		return productDao.getProduct(productCode, productName);
	}
	
	public List<Product> getAllProduct(){
		return productDao.getAllProduct();
	}
	
	public boolean chkProductCodeNotExisting(String code){
		return productDao.doChkProductCodeNotExisting(code);
	}
	
	public Product saveAndUpdateProduct(Product product){
		return productDao.merge(product);
	}
	
	public List<Inventory> getInventoryList(String productCode){
		return inventoryDao.getInventoryList(productCode);
	}
	
	public List<ProductAttach> getProductAttachByProductCode(String productCode){
		return productAttachDao.getProductAttachByProductCode(productCode);
	}
	
	public ProductAttach saveProductAttach(ProductAttach productAttach){
		return productAttachDao.merge(productAttach);
	}
	
	//======= GETTER, SETTER =======
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public ProductAttachDao getProductAttachDao() {
		return productAttachDao;
	}

	public void setProductAttachDao(ProductAttachDao productAttachDao) {
		this.productAttachDao = productAttachDao;
	}
	
}
