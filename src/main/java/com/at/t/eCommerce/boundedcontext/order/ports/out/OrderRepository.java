package com.at.t.eCommerce.boundedcontext.order.ports.out;

import java.util.Optional;

import com.at.t.eCommerce.boundedcontext.order.domain.model.Order;

public interface OrderRepository {

	Order save(Order order);

	Optional<Order> findById(String orderId);

}
