package com.at.t.eCommerce.boundedcontext.order.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.at.t.eCommerce.boundedcontext.order.domain.valueobject.OrderId;

import lombok.Getter;

@Getter
public class Order {

    private final OrderId orderId;
    private final String userId;
    private final BigDecimal total;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private OrderStatus status;
    private List<OrderItem> items;

    public Order(
        OrderId orderId,
        String userId,
        BigDecimal total,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<OrderItem> items
    ) {
        this.orderId = orderId;
        this.userId = userId;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items;
    }

    public Order(OrderId orderId, String userId, BigDecimal total) {
        this(
            orderId,
            userId,
            total,
            OrderStatus.PENDING,
            LocalDateTime.now(),
            LocalDateTime.now(),
            List.of()
        );
    }

    public void markPaid() {
        this.status = OrderStatus.PROCESSING;
        this.updatedAt = LocalDateTime.now();
    }

    public void complete() {
        this.status = OrderStatus.COMPLETED;
        this.updatedAt = LocalDateTime.now();
    }

	public void addItems(List<OrderItem> items) {	
		this.items = List.copyOf(items);
	}
}
