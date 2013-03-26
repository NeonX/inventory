package com.shop.inventory.dao;

import java.util.List;

import javax.persistence.Query;

import com.shop.inventory.entity.Inventory;

public class InventoryDao extends AbstractGenericDao<Inventory, Long> {

	public InventoryDao() {
		super(InventoryDao.class, Inventory.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Inventory> getInventoryList(String productCode){
		try{
			if(productCode != null && productCode.trim().length() > 0){
				String hql = "FROM Inventory WHERE product.productCode = :code ";
				Query q = getEntityManager().createQuery(hql);
				q.setParameter("code", productCode);
				return q.getResultList();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Inventory getInventoryById(Long inventoryId){
		try{
			if(inventoryId != null && inventoryId > 0){
				String hql = "FROM Inventory WHERE id = :ivnid ";
				Query q = getEntityManager().createQuery(hql);
				q.setParameter("ivnid", inventoryId);
				
				List<Inventory> result = q.getResultList();
				if(result != null && result.size() > 0){
					return result.get(0);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
