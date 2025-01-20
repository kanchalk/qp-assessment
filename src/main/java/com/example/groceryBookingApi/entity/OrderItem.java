package com.example.groceryBookingApi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "grocery_order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long groceryItemId;
    private int quantity;
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGroceryItemId() {
        return groceryItemId;
    }

    public void setGroceryItemId(Long groceryItemId) {
        this.groceryItemId = groceryItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
