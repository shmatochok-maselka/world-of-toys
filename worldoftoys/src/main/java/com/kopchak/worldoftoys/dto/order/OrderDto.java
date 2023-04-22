package com.kopchak.worldoftoys.dto.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private RecipientDto recipientDto;
    private AddressDto addressDto;
    private ShippingOptionDto shippingOptionDto;
}
