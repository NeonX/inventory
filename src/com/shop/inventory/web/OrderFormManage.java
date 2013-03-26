package com.shop.inventory.web;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.common.OperationMode;
import com.shop.inventory.common.OrderStatus;
import com.shop.inventory.entity.Inventory;
import com.shop.inventory.entity.Order;
import com.shop.inventory.entity.OrderDetail;
import com.shop.inventory.entity.Product;
import com.shop.inventory.entity.ProductAttach;
import com.shop.inventory.service.OrderService;
import com.shop.inventory.service.ProductService;
import com.shop.inventory.utils.AppUtils;

@Name("orderFormManage")
@Scope(ScopeType.PAGE)
public class OrderFormManage extends AbstractBackingBean<OrderFormManage> {

	private ProductService productService = (ProductService) getContextBackingBean().getBean("productService");
	private OrderService orderService = (OrderService) getContextBackingBean().getBean("orderService");
	
	private Order order;
	private List<Product> productList;
	private List<Inventory> inventories;
	
	private List<String> orderStatus;
	private String orderStatusSelected;
	private String orderDateStr;
	private int operationMode;
	
	private String productCodeSearch;
	private String productNameSearch;
	private Boolean searchResultEmpty;
	
	private Double sumPrice = 0D;
	
	private Boolean saveSuccess;
	private Boolean hasReserveFail;
	
	@In(required=false,scope=ScopeType.SESSION)
	@Out(required=false,scope=ScopeType.SESSION)
	private String currentOrderOperate;
	
	public OrderFormManage() {
		super(OrderFormManage.class);
	}

	@Create
	public void init(){
		if(currentOrderOperate == null || currentOrderOperate.trim().length() == 0){
			prepareNewOrder();
		}else{
			prepareEditOrder();
		}
		
		if(orderStatus == null){
			prepareOrderStatus();
		}
		
	}
	
	private void prepareNewOrder(){
		operationMode = OperationMode.NEW;
		order = new Order();
		order.setOrderCode(generateOrderCode());
		order.setCreate_date(new Date());
		
		orderDateStr = AppUtils.dateToString(new Date(), "dd/MM/yyyy");
	}
	
	private void prepareEditOrder(){
		operationMode = OperationMode.EDIT;
		order = orderService.getOrderByCode(currentOrderOperate);
	}
	
	public void doAddOrderDetail(Inventory inventory){
		if(inventory != null){
			
			int quantity = inventory.getQuantity().intValue();
			int available = inventory.getAvailable();
			
			OrderDetail tmpDetail = getExistingOrderDetail(inventory.getProduct().getProductCode(), inventory.getSize(), inventory.getColor());
			if(tmpDetail != null){
				
				quantity += tmpDetail.getQuantity();
				if(available > 0 && quantity > 0 && quantity <= available){
					tmpDetail.setQuantity(quantity);
				}
				
			}else{
				
				if(available > 0 && quantity > 0 && quantity <= available){
					try{
						OrderDetail detail = new OrderDetail();
						detail.setQuantity(quantity);
						detail.setSize(inventory.getSize());
						detail.setColor(inventory.getColor());
						detail.setSellPrice(inventory.getProduct().getSellPrice());
						detail.setProduct(inventory.getProduct());
						detail.setOrder(order);
						detail.setInventoryId(inventory.getId());//==== @Transient
						if(order.getOrderDetail() == null){
							order.setOrderDetail(new LinkedList<OrderDetail>());
						}
						order.getOrderDetail().add(detail);
						sumOrderPrice();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
			
		}
	}
	
	private OrderDetail getExistingOrderDetail(String productCode, String size, String color){
		OrderDetail result = null;
		if(order.getOrderDetail() != null){
			String paramCompare = productCode+size+color;
			for(OrderDetail detail : order.getOrderDetail()){
				String detailCompare = detail.getProduct().getProductCode()+detail.getSize()+detail.getColor();
				if(paramCompare.equals(detailCompare)){
					result = detail;
					break;
				}
			}
		}
		return result;
	}
	
	public void doSaveOrder(){
		order.setOrderDate(AppUtils.stringToDate(orderDateStr));
		order.setCreate_date(new Date());
		order.setStatus(orderStatusSelected);
		
		try{
			hasReserveFail = false;
			saveSuccess = false;
			if(order.getOrderDetail() != null && order.getOrderDetail().size() > 0){
				for(OrderDetail detail : order.getOrderDetail()){
					Inventory tmpInven = productService.getInventoryById(detail.getInventoryId());
					if(tmpInven != null){
						int available = tmpInven.getAvailable();
						int quantity = detail.getQuantity();
						if((available - quantity) >= 0){
							tmpInven.setAvailable(available - quantity);
							tmpInven = productService.saveAndUpdateInventory(tmpInven);
							
							detail.setProduct(tmpInven.getProduct());
							detail.setReserveComplete(true);
						}else{
							hasReserveFail = true;//==== quantity reserve is not available
						}
					}
				}
				
				order = orderService.saveAndUpdateOrder(order);
				saveSuccess = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
//	public void doAddOrderDetail(Inventory inventory){
//		if(inventory != null){
//			
//			int quantity = inventory.getQuantity().intValue();
//			
//			inventory = productService.getInventoryById(inventory.getId());
//			int available = inventory.getAvailable();
//			
//			if(available > 0 && quantity > 0 && quantity <= available){
//				inventory.setAvailable(available-quantity);
//				try{
//					inventory = productService.saveAndUpdateInventory(inventory);
//					OrderDetail detail = new OrderDetail();
//					detail.setQuantity(quantity);
//					detail.setSize(inventory.getSize());
//					detail.setColor(inventory.getColor());
//					detail.setSellPrice(inventory.getProduct().getSellPrice());
//					detail.setProduct(inventory.getProduct());
//					detail.setOrder(order);
//					if(order.getOrderDetail() == null){
//						order.setOrderDetail(new LinkedList<OrderDetail>());
//					}
//					order.getOrderDetail().add(detail);
//					order = orderService.saveAndUpdateOrder(order);
//					sumOrderPrice();
//					getProductInventory(inventory.getProduct().getProductCode());
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}else{
//				//=== quantity is not available
//			}
//		}
//	}
	
	private void sumOrderPrice(){
		sumPrice = 0D;
		if(order != null && order.getOrderDetail() != null){
			for(OrderDetail detail : order.getOrderDetail()){
				sumPrice += detail.getSellPrice();
			}
		}
	}
	
	public void doCancelOrder(){
		if(OperationMode.NEW == operationMode){
			
		}else if(OperationMode.EDIT == operationMode){
			
		}
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

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getCurrentOrderOperate() {
		return currentOrderOperate;
	}

	public void setCurrentOrderOperate(String currentOrderOperate) {
		this.currentOrderOperate = currentOrderOperate;
	}

	public Boolean getSaveSuccess() {
		return saveSuccess;
	}

	public void setSaveSuccess(Boolean saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

	public Boolean getHasReserveFail() {
		return hasReserveFail;
	}

	public void setHasReserveFail(Boolean hasReserveFail) {
		this.hasReserveFail = hasReserveFail;
	}

	
	
}
