package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.ShippingOptionDto;

import java.util.Set;

public interface OrderService {
    Set<ShippingOptionDto> getAllShippingOptions();
}
