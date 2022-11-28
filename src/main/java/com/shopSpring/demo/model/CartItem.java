package com.shopSpring.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long prodId;
    private String title;
    private Long quantity;
    private Long pricePerProduct;
    private Long totalPrice;

    public void changeQuantity(Long delta){
        quantity+=delta;
        totalPrice = pricePerProduct*quantity;
    }
}
