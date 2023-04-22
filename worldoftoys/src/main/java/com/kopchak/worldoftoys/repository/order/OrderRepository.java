package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
