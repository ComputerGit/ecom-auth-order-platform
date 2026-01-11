package com.at.t.eCommerce.boundedcontext.order.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.at.t.eCommerce.boundedcontext.order.domain.model.Order;
import com.at.t.eCommerce.boundedcontext.order.domain.model.OrderStatus;
import com.at.t.eCommerce.boundedcontext.order.domain.valueobject.OrderId;

public class OrderTest {

	@Test
	void order_starts_as_pending() {

		Order order = new Order(OrderId.newId(), "user-123", BigDecimal.valueOf(100));

		assertEquals(OrderStatus.PENDING, order.getStatus());
	}

	@Test
	void order_moves_to_processing_when_paid() {

		Order order = new Order(OrderId.newId(), "user=121", BigDecimal.valueOf(101));
		
		order.markPaid();
		
		assertEquals(OrderStatus.PROCESSING, order.getStatus());
	}
	
	@Test
	void order_moves_to_completed_when_complete () {
		
		Order order = new Order(OrderId.newId(), "123-user" , BigDecimal.valueOf(23));
		
		order.complete();
		
		assertEquals(OrderStatus.COMPLETED, order.getStatus());
	}
}
