package com.shop.inventory.utils.page.pagenav;

import java.util.LinkedList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class PageNavigationManage<T>  {

	protected List<T> dataList;
	
	protected Integer rows = 10;
	protected List<String> pagesNumList = new LinkedList<String>();
	protected String currentPage = "1";
	protected Long recordsCount = 0L;
	
	public PageNavigationManage(){}
	
	
	
	protected abstract void getListingData();
	
	protected void preparePageNum(Long recordsCount){
		this.recordsCount = recordsCount;
		
		pagesNumList.clear();
		//currentPage = "1";
		double pagesSize = Math.ceil(recordsCount.doubleValue()/rows.doubleValue());
		for(int num=0; num<pagesSize; num++){
			Integer pnum = num+1;
			pagesNumList.add(pnum.toString());
		}
		
		Integer tmpPageNo = Integer.parseInt(currentPage);
		if(tmpPageNo > pagesNumList.size()){
			currentPage = "1";
		}//*/
	}
	
	protected int getOffset(){
		return (rows*Integer.parseInt(currentPage))-rows;
	}
	
	public void findNextPage(){
		Integer pageNoInt = Integer.parseInt(currentPage);
		if(pageNoInt < pagesNumList.size()){
			currentPage = new Integer(pageNoInt+1).toString();
			getListingData();
		}
	}
	
	public void findPreviousPage(){
		Integer pageNoInt = Integer.parseInt(currentPage);
		if(pageNoInt > 1){
			currentPage = new Integer(pageNoInt-1).toString();
			getListingData();
		}
	}
	
	public void findFirstPage(){
		Integer pageNoInt = Integer.parseInt(currentPage);
		if(pageNoInt > 1){
			currentPage = "1";
			getListingData();
		}
	}
	
	public void findLastPage(){
		Integer pageNoInt = Integer.parseInt(currentPage);
		Integer pageNumSize = pagesNumList.size();
		if(pageNoInt < pageNumSize){
			currentPage = pageNumSize.toString();
			getListingData();
		}
	}
	
	public void doSwitchPage(){
		getListingData();
	}

	public void doRefresh(){
		getListingData();
		preparePageNum(recordsCount);
	}
	
	protected static ApplicationContext getContextBackingBean(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		ServletContext sc = (ServletContext)ec.getContext();
		ApplicationContext ac = (ApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
		return ac;
	}
	
	/*===================== GETTER, SETTER =====================*/
	
	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public List<String> getPagesNumList() {
		return pagesNumList;
	}

	public void setPagesNumList(List<String> pagesNumList) {
		this.pagesNumList = pagesNumList;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public Long getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(Long recordsCount) {
		this.recordsCount = recordsCount;
	}

	public Integer getRows() {
		return rows;
	}
	
}
