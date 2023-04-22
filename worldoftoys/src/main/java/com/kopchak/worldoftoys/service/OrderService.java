package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.order.OrderDto;
import com.kopchak.worldoftoys.dto.order.ShippingOptionDto;

import java.security.Principal;
import java.util.Set;

public interface OrderService {
    Set<ShippingOptionDto> getAllShippingOptions();

    void createOrder(OrderDto orderDto, Principal principal);
}
