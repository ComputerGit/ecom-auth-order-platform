package com.at.t.eCommerce.boundedcontext.order.api.dto;

import java.math.BigDecimal;

public class OrderItemRequest {
	
	public String productId;
    public String name;
    public BigDecimal price;
    public int quantity;
	
}
