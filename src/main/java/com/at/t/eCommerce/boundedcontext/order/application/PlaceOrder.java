package com.at.t.eCommerce.boundedcontext.order.application;

import java.math.BigDecimal;
import java.util.List;

import com.at.t.eCommerce.boundedcontext.order.domain.model.Order;
import com.at.t.eCommerce.boundedcontext.order.domain.model.OrderItem;
import com.at.t.eCommerce.boundedcontext.order.domain.valueobject.OrderId;
import com.at.t.eCommerce.boundedcontext.order.ports.out.OrderRepository;

public class PlaceOrder {

	private final OrderRepository orderRepository;

	public PlaceOrder(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order place(String userId, List<OrderItem> items) {

		BigDecimal total = items.stream().map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity()))).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		
		Order order = new Order(OrderId.newId(), userId, total);
		
		order.addItems(items);

		return orderRepository.save(order);
	}

}
