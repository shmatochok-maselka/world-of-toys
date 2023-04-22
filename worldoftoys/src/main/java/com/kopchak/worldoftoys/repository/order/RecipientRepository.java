package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.order.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Integer> {
}
