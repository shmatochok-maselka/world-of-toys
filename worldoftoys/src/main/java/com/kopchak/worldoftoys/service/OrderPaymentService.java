package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.order.*;

import java.security.Principal;
import java.util.Set;

public interface OrderPaymentService {
    Set<ShippingOptionDto> getAllShippingOptions();

    OrderDetailsDto makeOrder(OrderCreationDto orderCreationDto, Principal principal);
    void makeShippingPayment(PaymentCreationDto paymentCreationDto, Principal principal);
    void refund(Refund refund);
}
