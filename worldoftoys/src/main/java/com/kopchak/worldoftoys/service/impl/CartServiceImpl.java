package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.CartItemResponseDTO;
import com.kopchak.worldoftoys.dto.CartItemRequestDTO;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.CartItemId;
import com.kopchak.worldoftoys.model.CartItem;
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
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemsRepository cartItemsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void addProductToCart(CartItemRequestDTO cartItemRequestDTO, Principal principal){
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(() ->
            new UserNotFoundException(HttpStatus.NOT_FOUND, "User does not exist!"));
        Product product = productRepository.findBySlug(cartItemRequestDTO.getSlug());
        if(cartItemRequestDTO.getSlug() == null || product == null){
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(new CartItemId(user, product));
        cartItemsRepository.save(cartItem);
    }

    @Override
    public Set<CartItemResponseDTO> getCartProducts(Principal principal){
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.NOT_FOUND, "User does not exist!"));
        return cartItemsRepository.findAllProductsByUserId(user.getId());
    }

    @Override
    public void updateCartItemQuantity(CartItemRequestDTO cartItemRequestDTO, Principal principal){
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.NOT_FOUND, "User does not exist!"));
        Product product = productRepository.findBySlug(cartItemRequestDTO.getSlug());
        if(cartItemRequestDTO.getSlug() == null || product == null){
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        CartItem cartItem = cartItemsRepository.findById(new CartItemId(user, product)).orElseThrow(() ->
            new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product does not exist!"));
        cartItemsRepository.updateCartItemQuantity(cartItem.getId(), cartItemRequestDTO.getQuantity());
    }

    @Override
    public void removeProductFromCart(CartItemRequestDTO cartItemRequestDTO, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.NOT_FOUND, "User does not exist!"));
        Product product = productRepository.findBySlug(cartItemRequestDTO.getSlug());
        if(cartItemRequestDTO.getSlug() == null || product == null){
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        cartItemsRepository.deleteCartItemsById(new CartItemId(user, product));
    }
}
