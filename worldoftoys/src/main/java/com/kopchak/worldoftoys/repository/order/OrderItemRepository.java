package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.Product;
import com.kopchak.worldoftoys.model.order.OrderItem;
import com.kopchak.worldoftoys.model.order.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    @Query("SELECT oi FROM OrderItem oi WHERE oi.id.order.id = :orderId")
    Set<OrderItem> searchAllByOrderId(@Param("orderId") Integer orderId);
}
