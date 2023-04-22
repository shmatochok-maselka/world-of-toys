package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.order.OrderItem;
import com.kopchak.worldoftoys.model.order.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
