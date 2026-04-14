package com.at.t.eCommerce.boundedcontext.order.domain.model;

import java.math.BigDecimal;

public class OrderItem {
	   private String productId;
	    private String name;
	    private BigDecimal price;
	    private int quantity;
	    
	    public String getProductId() { return productId; }
	    public String getName() { return name; }
	    public BigDecimal getPrice() { return price; }
	    public int getQuantity() { return quantity; }
	    
	    public void setProductId(String productId) { this.productId = productId; }
	    public void setName(String name) { this.name = name; }
	    public void setPrice(BigDecimal price) { this.price = price; }
	    public void setQuantity(int quantity) { this.quantity = quantity; }
}
