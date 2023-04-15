package com.kopchak.worldoftoys.repository;

import com.kopchak.worldoftoys.model.CartItemId;
import com.kopchak.worldoftoys.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, CartItemId> {
}
