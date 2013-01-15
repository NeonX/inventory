package com.shop.inventory.web;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.common.OutcomeState;

@Name("menuPageManage")
@Scope(ScopeType.PAGE)
public class MenuPageManage extends AbstractBackingBean<MenuPageManage> {

	public MenuPageManage() {
		super(MenuPageManage.class);
	}

	@Create
	public void init(){}
		
	public String directToProductList(){
		return OutcomeState.SUCCESS;
	}

	public String directToOrderList(){
		return OutcomeState.SUCCESS;
	}
}
