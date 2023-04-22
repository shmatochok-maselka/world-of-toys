package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.ShippingOptionDto;
import com.kopchak.worldoftoys.repository.order.ShippingRepository;
import com.kopchak.worldoftoys.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShippingRepository shippingRepository;

    @Override
    public Set<ShippingOptionDto> getAllShippingOptions() {
        return shippingRepository.findAll()
                .stream()
                .map(ShippingOptionDto::new)
                .collect(Collectors.toSet());
    }
}
