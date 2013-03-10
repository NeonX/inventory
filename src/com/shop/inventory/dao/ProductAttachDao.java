package com.shop.inventory.dao;

import java.util.List;

import javax.persistence.Query;

import com.shop.inventory.entity.ProductAttach;

public class ProductAttachDao extends AbstractGenericDao<ProductAttach, Long> {

	public ProductAttachDao() {
		super(ProductAttachDao.class, ProductAttach.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<ProductAttach> getProductAttachByProductCode(String productCode){
		try{
			if(productCode != null && productCode.trim().length() > 0){
				String hql = "FROM ProductAttach WHERE product.productCode = :code ";
				Query q = getEntityManager().createQuery(hql);
				q.setParameter("code", productCode);
				return q.getResultList();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
