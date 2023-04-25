package com.kopchak.worldoftoys.model.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id
    @NonNull
    private String id;

    @NonNull
    private BigDecimal amount;

    @NonNull
    @Enumerated(EnumType.STRING)
    private PaymentCurrency currency = PaymentCurrency.UAH;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Source is mandatory")
    private String source;

}
