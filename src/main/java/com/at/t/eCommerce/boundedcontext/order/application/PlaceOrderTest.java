package com.at.t.eCommerce.boundedcontext.order.application;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.at.t.eCommerce.boundedcontext.order.domain.model.Order;
import com.at.t.eCommerce.boundedcontext.order.domain.model.OrderItem;
import com.at.t.eCommerce.boundedcontext.order.domain.model.OrderStatus;
import com.at.t.eCommerce.boundedcontext.order.ports.out.OrderRepository;

public class PlaceOrderTest {

	@Test
	void places_order_and_calculates_total() {

		OrderRepository fakeRepo = new InMemoryOrderRepo();
		PlaceOrder placeOrder = new PlaceOrder(fakeRepo);

		OrderItem item = new OrderItem();

		item.setProductId("prd-123");
		item.setName("phone");
		item.setPrice(BigDecimal.valueOf(100));
		item.setQuantity(3);

		Order order = placeOrder.place("user-123", List.of(item));

		assertEquals(BigDecimal.valueOf(300), order.getTotal());
		assertEquals(OrderStatus.PENDING, order.getStatus());
		assertNotNull(order.getOrderId());
	}

	class InMemoryOrderRepo implements OrderRepository {

		private Order saved;

		@Override
		public Order save(Order order) {
			this.saved = order;
			return order;
		}

		@Override
		public Optional<Order> findById(String orderId) {

			return Optional.ofNullable(saved);
		}

	}
}
