package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.order.OrderCreationDto;
import com.kopchak.worldoftoys.dto.order.OrderDetailsDto;
import com.kopchak.worldoftoys.dto.order.PaymentCreationDto;
import com.kopchak.worldoftoys.dto.order.ShippingOptionDto;

import java.security.Principal;
import java.util.Set;

public interface OrderPaymentService {
    Set<ShippingOptionDto> getAllShippingOptions();

    OrderDetailsDto makeOrder(OrderCreationDto orderCreationDto, Principal principal);
    void makeShippingPayment(PaymentCreationDto paymentCreationDto);
}
