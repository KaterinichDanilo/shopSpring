package com.shopSpring.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long prodId;
    private String title;
    private int quantity;
    private int pricePerProduct;
    private int totalPrice;

    public void changeQuantity(int delta){
        quantity += delta;
        totalPrice = pricePerProduct * quantity;
    }
}
