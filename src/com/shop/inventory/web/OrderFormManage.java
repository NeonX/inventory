package com.shop.inventory.web;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.common.OrderStatus;
import com.shop.inventory.entity.Inventory;
import com.shop.inventory.entity.Order;
import com.shop.inventory.entity.Product;
import com.shop.inventory.entity.ProductAttach;
import com.shop.inventory.service.ProductService;
import com.shop.inventory.utils.AppUtils;

@Name("orderFormManage")
@Scope(ScopeType.PAGE)
public class OrderFormManage extends AbstractBackingBean<OrderFormManage> {

	private ProductService productService = (ProductService) getContextBackingBean().getBean("productService");
	private Order order;
	private List<Product> productList;
	private List<Inventory> inventories;
	
	private List<String> orderStatus;
	private String orderStatusSelected;
	private String orderDateStr;
	
	private String productCodeSearch;
	private String productNameSearch;
	private Boolean searchResultEmpty;
	
	public OrderFormManage() {
		super(OrderFormManage.class);
	}

	@Create
	public void init(){
		prepareNewOrder();
		if(orderStatus == null){
			prepareOrderStatus();
		}
		
	}
	
	private void prepareNewOrder(){
		order = new Order();
		order.setOrderCode(generateOrderCode());
	}
	
	private String generateOrderCode(){
		
		Date today = new Date();
		String ordCodeGen = "ORD-"+AppUtils.dateToString(today,"ddMMyy",Locale.US);
		
		String timeStamp = new Long(today.getTime()).toString();
		ordCodeGen += "-"+timeStamp.substring(timeStamp.length()-4, timeStamp.length());
		
		return ordCodeGen;
	}
	
	private void prepareOrderStatus(){
		orderStatus = new LinkedList<String>();
		orderStatus.add(OrderStatus.RESERVE);
		orderStatus.add(OrderStatus.PAYABLE);
		orderStatus.add(OrderStatus.SHIPMENT);
		orderStatus.add(OrderStatus.COMPLETE);
		orderStatusSelected = OrderStatus.PAYABLE;//set default status
	}

	public void verifyStatus(){
		log.info("--- verifyStatus ---");
		if(OrderStatus.SHIPMENT.equals(orderStatusSelected)){
			
		}else if(OrderStatus.COMPLETE.equals(orderStatusSelected)){
			
		}
	}
	
	public void prepareFindProduct(){
		productCodeSearch = null;
		productNameSearch = null;
		searchResultEmpty = null;
		inventories = null;
		productList = productService.getAllProduct();
		prepareAttachment();
	}

	private void prepareAttachment(){
		for(Product pd : productList){
			if(pd.getImgAttach() != null && pd.getImgAttach().size() > 0){
				ProductAttach ath = pd.getImgAttach().get(0);
				pd.setFileServletUrl(AppUtils.getServletImgUrl(pd.getProductCode(), ath.getReferFilename(), ath.getAttachType()));
			}
		}
	}
	
	public void doSearchProduct(){
		if ((productCodeSearch != null && productCodeSearch.trim().length() > 0)
				|| (productNameSearch != null && productNameSearch.trim().length() > 0)) {
			
			productList.clear();
			searchResultEmpty = null;
			inventories = null;
			Product searchResult = productService.getProduct(productCodeSearch, productNameSearch);
			if(searchResult == null){
				searchResultEmpty = true;
			}else{
				productList.add(searchResult);
				prepareAttachment();
			}
		}
	}
	
	public void getProductInventory(String code){
		if(code != null && code.trim().length() > 0){
			inventories = productService.getInventoryList(code);
		}
	}
	
	//========= GETTER, SETTER ==========
	public List<String> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(List<String> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusSelected() {
		return orderStatusSelected;
	}

	public void setOrderStatusSelected(String orderStatusSelected) {
		this.orderStatusSelected = orderStatusSelected;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOrderDateStr() {
		return orderDateStr;
	}

	public void setOrderDateStr(String orderDateStr) {
		this.orderDateStr = orderDateStr;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getProductCodeSearch() {
		return productCodeSearch;
	}

	public void setProductCodeSearch(String productCodeSearch) {
		this.productCodeSearch = productCodeSearch;
	}

	public String getProductNameSearch() {
		return productNameSearch;
	}

	public void setProductNameSearch(String productNameSearch) {
		this.productNameSearch = productNameSearch;
	}

	public Boolean getSearchResultEmpty() {
		return searchResultEmpty;
	}

	public void setSearchResultEmpty(Boolean searchResultEmpty) {
		this.searchResultEmpty = searchResultEmpty;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}
	
}
