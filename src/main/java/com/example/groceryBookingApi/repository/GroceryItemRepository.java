package com.example.groceryBookingApi.repository;

import com.example.groceryBookingApi.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
}
