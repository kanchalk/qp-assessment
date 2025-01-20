package com.example.groceryBookingApi.repository;

import com.example.groceryBookingApi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
