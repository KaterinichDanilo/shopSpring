package com.shopSpring.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartDto {
    private List<CartItemDto> cart;
    private int totalPrice;

    public List<CartItemDto> getCart() {
        return cart;
    }

    public void setCart(List<CartItemDto> cart) {
        this.cart = cart;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
