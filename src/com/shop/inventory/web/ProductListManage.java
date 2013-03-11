package com.shop.inventory.web;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.common.OutcomeState;
import com.shop.inventory.entity.Inventory;
import com.shop.inventory.entity.Product;
import com.shop.inventory.entity.ProductAttach;
import com.shop.inventory.service.ProductService;
import com.shop.inventory.utils.AppUtils;
import com.shop.inventory.web.utils.WebUtils;

@Name("productListManage")
@Scope(ScopeType.PAGE)
public class ProductListManage extends AbstractBackingBean<ProductListManage> {

	private ProductService productService = (ProductService) getContextBackingBean().getBean("productService");
	private List<Product> productList;
	private List<Inventory> inventories;
	private Integer sumInventory = 0;
	
	@Out(required=false, scope=ScopeType.SESSION)
	String productCodeParam;
	
	
	public ProductListManage() {
		super(ProductListManage.class);
		// TODO Auto-generated constructor stub
	}

	@Create
	public void init(){
		productList = productService.getAllProduct();
		for(Product pd : productList){
			if(pd.getImgAttach() != null && pd.getImgAttach().size() > 0){
				ProductAttach ath = pd.getImgAttach().get(0);
				pd.setFileServletUrl(getServletImgUrl(pd.getProductCode(), ath.getReferFilename(), ath.getAttachType()));
			}
		}
	}
	
	private String getServletImgUrl(String productCode, String fileName, String type){
		
		String url = null;
		String context = WebUtils.getHostContextUrl()+"/attach_file/projattach";
		
		String param1 = "projid="+productCode;
		String param2 = "fname="+fileName;
		String param3 = "ptype="+type;
		
		if (!AppUtils.isNullOrEmpty(productCode)
				&& !AppUtils.isNullOrEmpty(fileName)
				&& !AppUtils.isNullOrEmpty(type)) {
			url = context+"?"+param1+"&"+param2+"&"+param3;
		}
		
		return url;
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
	
	public void directToProductForm(String productCode){
		productCodeParam = productCode;
		forceRedirectPage("/product/productform.xhtml");
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
