package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.ProductCartDto;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.CartItemId;
import com.kopchak.worldoftoys.model.CartItems;
import com.kopchak.worldoftoys.model.Product;
import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.repository.CartItemsRepository;
import com.kopchak.worldoftoys.repository.ProductRepository;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemsRepository cartItemsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void addProductToCart(ProductCartDto productCartDto, Principal principal){
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(() ->
            new UserNotFoundException(HttpStatus.NOT_FOUND, "User does not exist!"));
        Product product = productRepository.findBySlug(productCartDto.getSlug());
        if(productCartDto.getSlug() == null || product == null){
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        CartItems cartItem = new CartItems();
        cartItem.setId(new CartItemId(user, product));
        cartItemsRepository.save(cartItem);
    }
}
