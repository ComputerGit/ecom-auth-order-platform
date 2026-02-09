package com.at.t.eCommerce.boundedcontext.order.adapters.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "order_id", nullable = false)
	    private OrderModel order;

	    // 🔑 Cross-service ID (ULID / Snowflake)
	    @Column(name = "product_id", nullable = false, length = 36)
	    private String productId;

	    // 🧾 Snapshot fields
	    @Column(nullable = false)
	    private String productName;

	    @Column(nullable = false)
	    private Double price;

	    @Column(nullable = false)
	    private Integer quantity;
    
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "product_id", nullable = false)
//  @NotNull
//  private ProductModel product;
}
