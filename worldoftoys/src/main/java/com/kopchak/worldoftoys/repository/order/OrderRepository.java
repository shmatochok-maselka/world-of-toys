package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order searchByDateTimeAndUser(LocalDateTime orderDateTime, User user);
    Set<Order> searchAllByUser(User user);
}
