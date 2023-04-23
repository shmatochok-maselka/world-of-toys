package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.order.Order;
import com.kopchak.worldoftoys.model.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Query("UPDATE Order o SET o.status = :orderStatus WHERE o.dateTime = :orderDateTime AND o.user.email = :email")
    void updateOrderStatus(@Param("orderDateTime") LocalDateTime orderDateTime, @Param("email") String email,
                           @Param("orderStatus") OrderStatus orderStatus);
}
