package com.kopchak.worldoftoys.repository;

import com.kopchak.worldoftoys.model.CartItemId;
import com.kopchak.worldoftoys.model.CartItems;
import com.kopchak.worldoftoys.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CartItemsRepository extends JpaRepository<CartItems, CartItemId> {

    @Query("SELECT ci.id.product FROM CartItems ci WHERE ci.id.user.id = :userId")
    Set<Product> findAllProductsByUserId(Integer userId);
}
