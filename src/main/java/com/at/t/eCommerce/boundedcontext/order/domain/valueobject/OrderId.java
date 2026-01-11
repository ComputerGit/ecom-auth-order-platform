package com.at.t.eCommerce.boundedcontext.order.domain.valueobject;

import com.github.f4b6a3.ulid.UlidCreator;

/**
 * OrderId is the business identity of an Order. It is NOT a database ID. It is
 * globally unique, time-ordered, and immutable.
 */
public class OrderId {

	private final String value;

	private OrderId(String value) {
		this.value = value;
	}

	public static OrderId newId() {
		return new OrderId(UlidCreator.getUlid().toString());
	}

	public static OrderId of(String value) {
		return new OrderId(value);
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
