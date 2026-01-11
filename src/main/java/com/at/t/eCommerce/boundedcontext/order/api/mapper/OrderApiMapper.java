package com.at.t.eCommerce.boundedcontext.order.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.at.t.eCommerce.boundedcontext.order.api.dto.OrderItemRequest;
import com.at.t.eCommerce.boundedcontext.order.api.dto.OrderResponse;
import com.at.t.eCommerce.boundedcontext.order.domain.model.Order;
import com.at.t.eCommerce.boundedcontext.order.domain.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderApiMapper {
	
	OrderItem toDomain(OrderItemRequest req);
	
	@Mapping(source =  "orderId.value", target = "orderId")
    @Mapping(source = "status", target = "status")
    OrderResponse toResponse(Order order);

}
