package com.at.t.eCommerce.boundedcontext.order.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderModelRepo extends JpaRepository<OrderModel , String> {

}
