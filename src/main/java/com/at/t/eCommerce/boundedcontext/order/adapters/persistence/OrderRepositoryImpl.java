package com.at.t.eCommerce.boundedcontext.order.adapters.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.at.t.eCommerce.boundedcontext.order.adapters.persistence.mapper.OrderMapper;
import com.at.t.eCommerce.boundedcontext.order.domain.model.Order;
import com.at.t.eCommerce.boundedcontext.order.ports.out.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	private final OrderModelRepo jpaRepo;

	public OrderRepositoryImpl(OrderModelRepo jpaRepo) {
		this.jpaRepo = jpaRepo;
	}

	@Override
	public Order save(Order order) {

		OrderModel entity = OrderMapper.toEntity(order);
		OrderModel saved = jpaRepo.save(entity);
		return OrderMapper.toDomain(saved);
	}

	@Override
	public Optional<Order> findById(String orderId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
