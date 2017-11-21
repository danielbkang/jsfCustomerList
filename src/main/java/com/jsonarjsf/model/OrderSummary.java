package com.jsonarjsf.model;

import com.jsonarjsf.hibernate.model.Order;
import com.jsonarjsf.hibernate.model.OrderDetail;
import com.jsonarjsf.hibernate.model.Product;

public class OrderSummary {
	private Product product;
	private Order order;
	private OrderDetail orderDetail;
	
	public OrderSummary(Product product, Order order, OrderDetail orderDetail) {
		super();
		this.product = product;
		this.order = order;
		this.orderDetail = orderDetail;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	
}
