package com.at.t.eCommerce.boundedcontext.order.api.dto;

import java.math.BigDecimal;

public class OrderResponse {
    public String orderId;
    public String userId;
    public BigDecimal total;
    public String status;
}
