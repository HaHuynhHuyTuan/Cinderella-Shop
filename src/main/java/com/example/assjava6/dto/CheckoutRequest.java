package com.example.assjava6.dto;


import lombok.Data;

import java.util.List;

@Data
public class CheckoutRequest {
    private String username;
    private String address;
    private List<CartItem> cart;

    @Data
    public static class CartItem {
        private Long productId;
        private Integer quantity;

        public CartItem() {
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public CheckoutRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }
}
