package com.at.t.eCommerce.boundedcontext.order.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.at.t.eCommerce.boundedcontext.order.application.PlaceOrder;
import com.at.t.eCommerce.boundedcontext.order.ports.out.OrderRepository;

@Configuration
public class OrderConfig {
	
	@Bean
	public PlaceOrder placeOrder(OrderRepository orderRepository) {
		return new PlaceOrder(orderRepository);
	}

}
