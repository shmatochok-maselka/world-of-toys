package com.kopchak.worldoftoys.repository.cart;

import com.kopchak.worldoftoys.dto.cart.CartItemResponseDto;
import com.kopchak.worldoftoys.model.cart.CartItemId;
import com.kopchak.worldoftoys.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CartItemsRepository extends JpaRepository<CartItem, CartItemId> {
    @Query("SELECT new com.kopchak.worldoftoys.dto.cart.CartItemResponseDto(p.name, p.slug, p.image, p.price, c.quantity) FROM CartItem c JOIN c.id.product p WHERE c.id.user.id = :userId")
    Set<CartItemResponseDto> findAllProductsByUserId(@Param("userId") Integer userId);

    @Query("SELECT c FROM CartItem c JOIN FETCH c.id.product WHERE c.id.user.id = :userId")
    Set<CartItem> findAllCartItemsByUserId(@Param("userId") Integer userId);
    void deleteCartItemsById(CartItemId cartItemId);

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.id.user.id = :userId")
    void deleteCartItemsByUserId(@Param("userId") Integer userId);

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :cartItemId")
    void updateCartItemQuantity(@Param("cartItemId") CartItemId cartItemId, @Param("quantity") Integer quantity);

    CartItem searchById(CartItemId cartItemId);
}
