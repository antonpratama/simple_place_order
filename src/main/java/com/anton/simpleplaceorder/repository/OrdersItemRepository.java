package com.anton.simpleplaceorder.repository;

import com.anton.simpleplaceorder.entity.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdersItemRepository extends JpaRepository<OrdersItem, UUID> {
}
