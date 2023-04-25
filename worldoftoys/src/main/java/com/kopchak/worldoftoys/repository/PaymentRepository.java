package com.kopchak.worldoftoys.repository;

import com.kopchak.worldoftoys.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
