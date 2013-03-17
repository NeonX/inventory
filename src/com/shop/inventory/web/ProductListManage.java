package com.shop.inventory.web;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.entity.Inventory;
import com.shop.inventory.entity.Product;
import com.shop.inventory.entity.ProductAttach;
import com.shop.inventory.service.ProductService;
import com.shop.inventory.utils.AppUtils;

@Name("productListManage")
@Scope(ScopeType.PAGE)
public class ProductListManage extends AbstractBackingBean<ProductListManage> {

	private ProductService productService = (ProductService) getContextBackingBean().getBean("productService");
	private List<Product> productList;
	private List<Inventory> inventories;
	private Integer sumInventory = 0;
	
	private String productCodeSearch;
	private String productNameSearch;
	private Boolean searchResultEmpty;
	
	@Out(required=false, scope=ScopeType.SESSION)
	String productCodeParam;
	
	
	public ProductListManage() {
		super(ProductListManage.class);
		// TODO Auto-generated constructor stub
	}

	@Create
	public void init(){
		productCodeSearch = null;
		productNameSearch = null;
		searchResultEmpty = null;
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
	
	public void getProductInventory(String code){
		if(code != null && code.trim().length() > 0){
			inventories = productService.getInventoryList(code);
			sumInventory = 0;
			if(inventories != null && inventories.size() > 0){
				for(Inventory item : inventories){
					sumInventory += item.getAvailable(); 
				}
			}
		}
	}
	
	public void doSearchProduct(){
		if ((productCodeSearch != null && productCodeSearch.trim().length() > 0)
				|| (productNameSearch != null && productNameSearch.trim().length() > 0)) {
			
			productList.clear();
			Product searchResult = productService.getProduct(productCodeSearch, productNameSearch);
			if(searchResult == null){
				searchResultEmpty = true;
			}else{
				productList.add(searchResult);
				prepareAttachment();
			}
		}
	}
	
	public void directToProductForm(){
		forceRedirectPage("/product/productform.xhtml");
	}
	
	public void directToProductForm(String productCode){
		productCodeParam = productCode;
		forceRedirectPage("/product/productform.xhtml");
	}
	
	public void directToOrderForm(){
		forceRedirectPage("/order/orderform.xhtml");
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

	public String getProductCodeSearch() {
		return productCodeSearch;
	}

	public void setProductCodeSearch(String productCodeSearch) {
		this.productCodeSearch = productCodeSearch;
	}

	public Boolean getSearchResultEmpty() {
		return searchResultEmpty;
	}

	public void setSearchResultEmpty(Boolean searchResultEmpty) {
		this.searchResultEmpty = searchResultEmpty;
	}

	public String getProductNameSearch() {
		return productNameSearch;
	}

	public void setProductNameSearch(String productNameSearch) {
		this.productNameSearch = productNameSearch;
	}
	
	
}
