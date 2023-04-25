package com.kopchak.worldoftoys.model.order;

import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.model.payment.Payment;
import jakarta.persistence.*;
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
    private LocalDateTime dateTime;

    @NonNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NonNull
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

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
