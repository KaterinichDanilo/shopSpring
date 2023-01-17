package com.shopSpring.core.repositories;

import com.shopSpring.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order);
}
