package com.example.groceryBookingApi.serviceImpl;

import com.example.groceryBookingApi.entity.GroceryItem;
import com.example.groceryBookingApi.entity.Order;
import com.example.groceryBookingApi.entity.OrderItem;
import com.example.groceryBookingApi.repository.GroceryItemRepository;
import com.example.groceryBookingApi.repository.OrderItemRepository;
import com.example.groceryBookingApi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroceryServiceImpl {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order createOrder(Long userId, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setUserId(userId);
        order.setCreatedAt(LocalDateTime.now());

        Long totalAmount = 0L;
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getId());
            orderItem.setPrice(groceryItemRepository.findById(orderItem.getGroceryItemId()).orElseThrow().getPrice());
            totalAmount=totalAmount+orderItem.getPrice();
            orderItemRepository.save(orderItem);
        }
        order.setTotalPrice(totalAmount);
        order = orderRepository.save(order);
        return order;
    }

    public GroceryItem addItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    public List<GroceryItem> getAllItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem updateItem(Long id, GroceryItem groceryItem) {
        groceryItem.setId(id);
        return groceryItemRepository.save(groceryItem);
    }

    public void deleteItem(Long id) {
        groceryItemRepository.deleteById(id);
    }

    public GroceryItem updateStock(Long id, int stockQuantity) {
        GroceryItem item = groceryItemRepository.findById(id).orElseThrow();
        item.setStockQuantity(stockQuantity);
        return groceryItemRepository.save(item);
    }

}

