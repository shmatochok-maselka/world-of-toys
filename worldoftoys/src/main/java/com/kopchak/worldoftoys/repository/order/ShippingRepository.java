package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.order.ShippingMethod;
import com.kopchak.worldoftoys.model.order.ShippingOption;
import com.kopchak.worldoftoys.model.order.ShippingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingOption, Integer> {
    ShippingOption findByTypeAndMethod(ShippingType shippingType, ShippingMethod shippingMethod);
}
