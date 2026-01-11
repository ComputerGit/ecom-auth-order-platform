package com.at.t.eCommerce.boundedcontext.order.api;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.at.t.eCommerce.boundedcontext.order.api.dto.CreateOrderRequest;
import com.at.t.eCommerce.boundedcontext.order.api.mapper.OrderApiMapper;
import com.at.t.eCommerce.boundedcontext.order.application.PlaceOrder;
import com.at.t.eCommerce.boundedcontext.order.domain.model.Order;
import com.at.t.eCommerce.boundedcontext.order.domain.model.OrderItem;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final PlaceOrder placeOrder;
	private final OrderApiMapper mapper;

	public OrderController(PlaceOrder placeOrder, OrderApiMapper mapper) {
		this.placeOrder = placeOrder;
		this.mapper = mapper;
	}
	
	public Order place(@AuthenticationPrincipal Jwt principal , @RequestBody CreateOrderRequest req) {
		
		String subject = principal.getSubject();
		
		List<OrderItem> list = req.items.stream().map(mapper :: toDomain).toList();
		
		return placeOrder.place(subject, list);
		
	}

}
