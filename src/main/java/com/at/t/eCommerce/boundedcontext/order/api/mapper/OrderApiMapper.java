package com.at.t.eCommerce.boundedcontext.order.api.mapper;

import org.mapstruct.Mapper;

import com.at.t.eCommerce.boundedcontext.order.api.dto.OrderItemRequest;
import com.at.t.eCommerce.boundedcontext.order.domain.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderApiMapper {
	
	OrderItem toDomain(OrderItemRequest req);

}
