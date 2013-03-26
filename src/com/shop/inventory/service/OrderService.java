package com.shop.inventory.service;

import com.shop.inventory.dao.OrderDao;
import com.shop.inventory.entity.Order;

public class OrderService {

	private OrderDao orderDao;

	public Order getOrderByCode(String orderCode){
		return orderDao.getOrderByCode(orderCode);
	}
	
	public Order saveAndUpdateOrder(Order order){
		return orderDao.merge(order);
	}
	
	//======== GETTER, SETTER ====
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	
}
