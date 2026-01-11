package com.at.t.eCommerce.boundedcontext.order.adapters.persistence.mapper;

import com.at.t.eCommerce.boundedcontext.order.domain.model.Order;
import com.at.t.eCommerce.boundedcontext.order.domain.model.OrderItem;
import com.at.t.eCommerce.boundedcontext.order.domain.valueobject.OrderId;
import com.at.t.eCommerce.boundedcontext.order.adapters.persistence.OrderItemModel;
import com.at.t.eCommerce.boundedcontext.order.adapters.persistence.OrderModel;

import java.math.BigDecimal;

public class OrderMapper {

    // Domain → JPA
    public static OrderModel toEntity(Order domain) {
        OrderModel m = new OrderModel();
        m.setOrderId(domain.getOrderId().getValue());   // ULID
        m.setUserId(domain.getUserId());
        m.setOrderStatus(OrderModel.OrderStatus.valueOf(domain.getStatus().name()));
        m.setTotalAmount(domain.getTotal().doubleValue());
        m.setCreatedAt(domain.getCreatedAt());
        m.setUpdatedAt(domain.getUpdatedAt());

        m.setOrderItems(
            domain.getItems().stream()
                .map(i -> toEntity(i, m))
                .toList()
        );

        return m;
    }

    private static OrderItemModel toEntity(OrderItem item, OrderModel order) {
        OrderItemModel m = new OrderItemModel();
        m.setOrder(order);
        m.setProductId(item.getProductId());      // ULID from product service
        m.setProductName(item.getName());         // snapshot
        m.setPrice(item.getPrice().doubleValue());
        m.setQuantity(item.getQuantity());
        return m;
    }

    // JPA → Domain
    public static Order toDomain(OrderModel m) {
        return new Order(
            OrderId.of(m.getOrderId()),
            m.getUserId(),
            BigDecimal.valueOf(m.getTotalAmount()),
            com.at.t.eCommerce.boundedcontext.order.domain.model.OrderStatus
                .valueOf(m.getOrderStatus().name()),
            m.getCreatedAt(),
            m.getUpdatedAt(),
            m.getOrderItems().stream()
                .map(OrderMapper::toDomain)
                .toList()
        );
    }




    private static OrderItem toDomain(OrderItemModel m) {
        OrderItem i = new OrderItem();
        i.setProductId(m.getProductId());
        i.setName(m.getProductName());
        i.setPrice(BigDecimal.valueOf(m.getPrice()));
        i.setQuantity(m.getQuantity());
        return i;
    }
}
