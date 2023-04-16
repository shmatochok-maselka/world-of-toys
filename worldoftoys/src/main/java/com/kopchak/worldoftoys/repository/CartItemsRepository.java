package com.kopchak.worldoftoys.repository;

import com.kopchak.worldoftoys.dto.CartItemResponseDTO;
import com.kopchak.worldoftoys.model.CartItemId;
import com.kopchak.worldoftoys.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CartItemsRepository extends JpaRepository<CartItem, CartItemId> {
    @Query("SELECT new com.kopchak.worldoftoys.dto.CartItemResponseDTO(p.name, p.slug, p.image, p.price, c.quantity) FROM CartItem c JOIN c.id.product p WHERE c.id.user.id = :userId")
    Set<CartItemResponseDTO> findAllProductsByUserId(@Param("userId") Integer userId);

    void deleteCartItemsById(CartItemId cartItemId);

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :cartItemId")
    void updateCartItemQuantity(@Param("cartItemId") CartItemId cartItemId, @Param("quantity") Integer quantity);
}
