package com.example.groceryBookingApi.repository;

import com.example.groceryBookingApi.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

