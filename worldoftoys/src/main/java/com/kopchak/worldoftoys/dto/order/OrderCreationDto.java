package com.kopchak.worldoftoys.dto.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationDto {
    private RecipientDto recipientDto;
    private AddressDto addressDto;
    private ShippingOptionDto shippingOptionDto;
}
