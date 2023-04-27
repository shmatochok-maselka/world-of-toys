package com.kopchak.worldoftoys.repository.order;

import com.kopchak.worldoftoys.model.order.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
