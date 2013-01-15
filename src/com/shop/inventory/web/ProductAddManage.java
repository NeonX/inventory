package com.shop.inventory.web;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.common.OutcomeState;

@Name("productAddManage")
@Scope(ScopeType.PAGE)
public class ProductAddManage extends AbstractBackingBean<ProductAddManage> {

	public ProductAddManage() {
		super(ProductAddManage.class);
		// TODO Auto-generated constructor stub
	}

	public String directToProductList(){
		return OutcomeState.SUCCESS;
	}
}
