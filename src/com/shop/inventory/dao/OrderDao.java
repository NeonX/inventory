package com.shop.inventory.dao;



import java.util.List;

import javax.persistence.Query;

import com.shop.inventory.entity.Order;

public class OrderDao extends AbstractGenericDao<Order, String> {

	public OrderDao() {
		super(OrderDao.class, Order.class);
	}

	@SuppressWarnings("unchecked")
	public Order getOrderByCode(String orderCode){
		try{
			if(orderCode != null && orderCode.trim().length() > 0){
				String hql = "FROM Order WHERE orderCode = :code ";
				Query q = getEntityManager().createQuery(hql);
				q.setParameter("code", orderCode);
				
				List<Order> orderList = q.getResultList();
				if(orderList != null && orderList.size() > 0){
					return orderList.get(0);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
