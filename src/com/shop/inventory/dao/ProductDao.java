package com.shop.inventory.dao;



import java.util.List;

import javax.persistence.Query;

import com.shop.inventory.entity.Product;

public class ProductDao extends AbstractGenericDao<Product, String> {

	public ProductDao() {
		super(ProductDao.class, Product.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public Product getProduct(String productCode){
		try{
			if(productCode != null && productCode.trim().length() > 0){
				String hql = "FROM Product WHERE productCode = :pcode ";
				Query q = getEntityManager().createQuery(hql);
				q.setParameter("pcode", productCode);
				
				List<Product> result = q.getResultList();
				if(result != null && result.size() > 0){
					return result.get(0);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct(){
		try{
			String hql = "FROM Product ORDER BY create_date DESC";
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public boolean doChkProductCodeNotExisting(String code){
		try{
			if(code != null && code.trim().length() > 0){
				String hql = "SELECT COUNT(productCode) FROM Product WHERE productCode = :pcode ";
				Query q = getEntityManager().createQuery(hql);
				q.setParameter("pcode", code);
				
				List<Long> resutList = q.getResultList();
				if(resutList != null && resutList.size() > 0){
					Long result = resutList.get(0);
					if(result <= 0){
						return true;
					}else{
						return false;//product code is existing
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
}
