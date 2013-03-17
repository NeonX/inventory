package com.shop.inventory.common;

import com.shop.inventory.utils.AppUtils;

public class OrderStatus {

	public static final String RESERVE = AppUtils.getMessageByEL("order.status.reserve");
	public static final String PAYABLE = AppUtils.getMessageByEL("order.status.payable");
	public static final String SHIPMENT = AppUtils.getMessageByEL("order.status.shipment");
	public static final String COMPLETE = AppUtils.getMessageByEL("order.status.complete");
}
