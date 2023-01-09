package com.shopSpring.cart.services;

import com.shopSpring.api.ProductDto;
import com.shopSpring.cart.integrations.ProductServiceIntegration;
import com.shopSpring.cart.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    private void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void add(Long id){
        ProductDto product = productServiceIntegration.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found id = " + id));
        cart.add(product);
    }

    public void remove(Long prodId){
        cart.remove(prodId);
    }

    public void clear(){
        cart.clear();
    }
}
