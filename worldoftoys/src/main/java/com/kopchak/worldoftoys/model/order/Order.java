package com.kopchak.worldoftoys.model.order;

import com.kopchak.worldoftoys.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime orderDateTime;

    @NonNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @NotBlank(message = "Total price is mandatory")
    private BigDecimal totalPrice;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @NonNull
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "shipping_id")
    private ShippingOption shippingOption;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "address_id")
    private Address address;
}
