package com.at.t.eCommerce.boundedcontext.order.adapters.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.model.PaymentModel;
import com.at.t.eCommerce.model.ShipmentModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    @Id
    @Column(name = "order_id", length = 36)
    private String orderId;     // ULID from domain

    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double totalAmount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItemModel> orderItems;

    public enum OrderStatus { PENDING, PROCESSING, COMPLETED, CANCELED }
    
//    @OneToMany(mappedBy = "order" ,fetch = FetchType.LAZY , cascade = CascadeType.ALL)
//    private List<PaymentModel> payments;
//    
//    @OneToMany(mappedBy = "order" ,fetch = FetchType.LAZY , cascade = CascadeType.ALL)
//    private List<ShipmentModel> shipments;
    
    
}
