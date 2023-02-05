package com.shopSpring.cart.model;

import com.shopSpring.api.ProductDto;

public interface CartItemInt {
    ProductDto consume(ProductDto product);
    void increase(Long prodId, int delta);
    void reduce(Long prodId, int delta);
}
