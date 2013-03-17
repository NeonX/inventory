package com.shop.inventory.web;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.model.UploadItem;

import com.shop.inventory.common.AttachType;
import com.shop.inventory.entity.Inventory;
import com.shop.inventory.entity.Product;
import com.shop.inventory.entity.ProductAttach;
import com.shop.inventory.service.ProductService;
import com.shop.inventory.utils.AppUtils;
import com.shop.inventory.wrapper.FileItem;

@Name("productFormManage")
@Scope(ScopeType.PAGE)
public class ProductFormManage extends AbstractUploadBackingBean<ProductFormManage> {

	private ProductService productService = (ProductService) getContextBackingBean().getBean("productService");
	private Product product;
	private Inventory inventory;
	private String sizeSelected;
	private boolean onEditMode = false;
	
	private List<ProductAttach> attachList = new LinkedList<ProductAttach>();
	private List<ProductAttach> tmpAttachList = new LinkedList<ProductAttach>();
	private List<String> fileOverList = new LinkedList<String>();
	private List<FileItem> fItems = new LinkedList<FileItem>();
	
	private Boolean saveStatus;
	
	@In(required=false, scope=ScopeType.SESSION)
	String productCodeParam;
	
	public ProductFormManage() {
		super(ProductFormManage.class);
		// TODO Auto-generated constructor stub
	}

	@Create
	public void init(){
		
		if(productCodeParam != null){
			product = productService.getProduct(productCodeParam);
			if(product != null){
				onEditMode = true;
				prepareAttachList();
			}else{
				prepareNewProduct();
			}
		}else{
			prepareNewProduct();
		}

	}

	public void prepareNewProduct(){
		saveStatus = null;
		inventory = null;
		attachList.clear();
		tmpAttachList.clear();
		fileOverList.clear();
		fItems.clear();
		sizeSelected = "Free size";
		
		product = new Product();
		product.setProductCode(generateProductCode());
		product.setInventories(new LinkedList<Inventory>());
	}
	
	private String generateProductCode(){
		
		String prdCodeGen = null;
		boolean isExisting = true;
		while(isExisting){
			prdCodeGen = "P"+AppUtils.generateProductCodeStr();
			if(productService.chkProductCodeNotExisting(prdCodeGen)){
				isExisting = false;
			}
		}
		return prdCodeGen;
	}
	
	public void prepareNewInventory(){
		inventory = new Inventory(product);
		sizeSelected = null;
	}
	
	public void prepareEditInventory(Inventory inventory){
		this.inventory = inventory;
		sizeSelected = this.inventory.getSize();
	}
	
	public void doAddInventory(){
		inventory.setSize(sizeSelected);
		doIncreaseProduct();
		product.getInventories().add(inventory);
	}
	
	public void doUpdateInventory(){
		inventory.setSize(sizeSelected);
		doIncreaseProduct();
	}
	
	public void prepareIncrease(Inventory inventory){
		this.inventory = inventory;
		this.inventory.setQuantity(0);
	}
	
	public void doIncreaseProduct(){
		inventory.setAvailable(inventory.getAvailable() + inventory.getQuantity());
		inventory.setBalance(inventory.getBalance() + inventory.getQuantity());
	}
	
	public void saveProduct(){
		saveStatus = false;
//		Integer quantityCount = 0;
//		for(Inventory inven : product.getInventories()){
//			inven.setAvailable(inven.getAvailable() + inven.getQuantity());
//			inven.setBalance(inven.getBalance() + inven.getQuantity());
//			quantityCount += inven.getQuantity();
//		}
		
		try{
			if(product.getCreate_date() == null){
				product.setCreate_by(null);
				product.setCreate_date(new Date());
			}else{
				product.setUpdate_by(null);
				product.setUpdate_date(new Date());
			}
			
			product.setImgAttach(tmpAttachList);
			product = productService.saveAndUpdateProduct(product);
			saveStatus = true;
			prepareAttachList();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void prepareAttachList() {
		tmpAttachList.clear();
		attachList = productService.getProductAttachByProductCode(product.getProductCode());
		for(ProductAttach item : attachList){
			item.setFileServletUrl(getServletImgUrl(product.getProductCode(), item.getReferFilename(), item.getAttachType()));
			item.setFileServletDownloadUrl(getServletDownloadUrl(product.getProductCode(), item.getReferFilename(), item.getAttachType()));
		}
		
//		prepareEmptyBean(attachList);
		
	}
	
	@SuppressWarnings("unused")
	private void prepareEmptyBean(List<ProductAttach> attachList){
		if(attachList != null && attachList.size() > 0){
			int size = attachList.size();
			int lastRowsNum = size % viewCol;
			
			if(lastRowsNum != 0){
				for(int count=lastRowsNum; count<viewCol; count++){
					attachList.add(getEmptyAttach());
				}
			}
		}
	}
	
	private ProductAttach getEmptyAttach(){
		ProductAttach emptyBean = new ProductAttach();
		emptyBean.setOriginFilename("");
		emptyBean.setFileServletUrl(getServletImgUrl(null, null, AttachType.BLANK));
		return emptyBean;
	}
	
	@Override
	public void specifyBean(UploadItem uploadItem) {
		// TODO Auto-generated method stub
		if (uploadItem.getFileSize() <= imgSizeBytesAllows) {

			try {
				String fNameToSave = getGenerateFileName("IMG")
						+ "." + getFileExtension(uploadItem.getFileName());
				prepareAndSaveAttach(fNameToSave, uploadItem);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			fileOverList.add(getFileName(uploadItem.getFileName()) + " "
					+ AppUtils.getMessageByEL("alert.file.over.postfix"));
		}
	}
	
	private void prepareAndSaveAttach(String fNameToSave, UploadItem uploadItem) throws Exception {
		
		if(fItems == null){
			fItems = new LinkedList<FileItem>();
		}
		
		FileItem file = new FileItem();
		file.setName(fNameToSave);
		file.setSizeBytes(uploadItem.getFileSize());
		file.setMime(AppUtils.getMimeType(uploadItem.getFileName()));
		file.setFileTmp(uploadItem.getFile());
		file.setProductRefCode(product.getProductCode());
		file.setAttachType("IMGS");
		fItems.add(file);

		ProductAttach attach = new ProductAttach();
		attach.setReferFilename(fNameToSave);
		attach.setOriginFilename(getFileName(uploadItem.getFileName()));
		attach.setFileSizeBytes(new Double(uploadItem.getFileSize()));
		attach.setAttachType(file.getAttachType());
		attach.setContentType(AppUtils.getMimeType(uploadItem.getFileName()));
		attach.setDescription(getNameWithOutExtension(attach
				.getOriginFilename()));
		attach.setCreate_by(credentials.getUsername());
		attach.setCreate_date(new Date());
		attach.setUpdate_by(credentials.getUsername());
		attach.setUpdate_date(new Date());
		attach.setProduct(product);
		tmpAttachList.add(attach);
	}
	
	public void doMoveFileFromTmp(){
		try {
			savefiles(fItems);
			fItems.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDoUploadImg(){
		fileOverList.clear();
		tmpAttachList.clear();
	}
	
	public void directToProductList(){
		forceRedirectPage("/product/productlist.xhtml");
	}
	
	
	//========= GETTER, SETTER ========
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public String getSizeSelected() {
		return sizeSelected;
	}

	public void setSizeSelected(String sizeSelected) {
		this.sizeSelected = sizeSelected;
	}

	public Boolean getSaveStatus() {
		return saveStatus;
	}

	public void setSaveStatus(Boolean saveStatus) {
		this.saveStatus = saveStatus;
	}

	public List<ProductAttach> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<ProductAttach> attachList) {
		this.attachList = attachList;
	}

	public List<String> getFileOverList() {
		return fileOverList;
	}

	public void setFileOverList(List<String> fileOverList) {
		this.fileOverList = fileOverList;
	}

	public List<ProductAttach> getTmpAttachList() {
		return tmpAttachList;
	}

	public void setTmpAttachList(List<ProductAttach> tmpAttachList) {
		this.tmpAttachList = tmpAttachList;
	}

	public boolean getOnEditMode() {
		return onEditMode;
	}

	public void setOnEditMode(boolean onEditMode) {
		this.onEditMode = onEditMode;
	}



}
