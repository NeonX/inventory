package com.shop.inventory.web;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.common.OutcomeState;
import com.shop.inventory.entity.Inventory;
import com.shop.inventory.entity.Product;
import com.shop.inventory.service.ProductService;

@Name("productListManage")
@Scope(ScopeType.PAGE)
public class ProductListManage extends AbstractBackingBean<ProductListManage> {

	private ProductService productService = (ProductService) getContextBackingBean().getBean("productService");
	private List<Product> productList;
	private List<Inventory> inventories;
	private Integer sumInventory = 0;
	
	public ProductListManage() {
		super(ProductListManage.class);
		// TODO Auto-generated constructor stub
	}

	@Create
	public void init(){
		productList = productService.getAllProduct();
	}
	
	public void getProductInventory(String code){
		if(code != null && code.trim().length() > 0){
			inventories = productService.getInventoryList(code);
			if(inventories != null && inventories.size() > 0){
				for(Inventory item : inventories){
					sumInventory += item.getAvailable(); 
				}
			}
		}
	}
	
	public String directToProductForm(){
		return OutcomeState.SUCCESS;
	}
	
	public String directToOrderAdd(){
		return OutcomeState.SUCCESS;
	}

	
	//========== GETTER, SETTER ===========
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Integer getSumInventory() {
		return sumInventory;
	}

	public void setSumInventory(Integer sumInventory) {
		this.sumInventory = sumInventory;
	}
	
	
}
