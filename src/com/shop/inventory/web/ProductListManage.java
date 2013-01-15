package com.shop.inventory.web;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.common.OutcomeState;

@Name("productListManage")
@Scope(ScopeType.PAGE)
public class ProductListManage extends AbstractBackingBean<ProductListManage> {

	public ProductListManage() {
		super(ProductListManage.class);
		// TODO Auto-generated constructor stub
	}

	public String directToProductAdd(){
		return OutcomeState.SUCCESS;
	}
	
	public String directToOrderAdd(){
		return OutcomeState.SUCCESS;
	}
}
