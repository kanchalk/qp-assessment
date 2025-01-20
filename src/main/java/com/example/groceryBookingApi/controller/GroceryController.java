package com.example.groceryBookingApi.controller;

import com.example.groceryBookingApi.entity.GroceryItem;
import com.example.groceryBookingApi.entity.Order;
import com.example.groceryBookingApi.entity.OrderItem;
import com.example.groceryBookingApi.serviceImpl.GroceryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grocery")
public class GroceryController {
    @Autowired
    private GroceryServiceImpl groceryService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/addItems")
    public GroceryItem addItem(@RequestBody GroceryItem groceryItem) {
        return groceryService.addItem(groceryItem);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/getAllItems")
    public List<GroceryItem> getAllItems() {
        return groceryService.getAllItems();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/updateItems/{id}")
    public GroceryItem updateItem(@PathVariable Long id, @RequestBody GroceryItem groceryItem) {
        return groceryService.updateItem(id, groceryItem);
    }
    //soft delete
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/removeItems/{id}")
    public void deleteItem(@PathVariable Long id) {
        groceryService.deleteItem(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/grocery-items/{id}/stock")
    public GroceryItem updateStock(@PathVariable Long id, @RequestParam int stockQuantity) {
        return groceryService.updateStock(id, stockQuantity);
    }


    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/orders")
    public Order createOrder(@RequestParam Long userId, @RequestBody List<OrderItem> orderItems) {
        return groceryService.createOrder(userId, orderItems);
    }

}
