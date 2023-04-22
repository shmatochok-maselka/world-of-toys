package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.order.ShippingOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingOption, Integer> {
}
