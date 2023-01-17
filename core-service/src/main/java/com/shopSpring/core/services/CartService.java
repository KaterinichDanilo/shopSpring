package com.shopSpring.core.services;

import com.shopSpring.core.model.Cart;
import com.shopSpring.core.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CartService {
    private Cart cart;
    private com.shopSpring.core.services.ProductService productService;

    @Autowired
    public void setProductRepository(com.shopSpring.core.services.ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    private void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void add(Long id){
        Product product = productService.findById(id).orElseThrow(() -> new RuntimeException("Product not found id = " + id));
        cart.add(product);
    }

    public void remove(Long prodId){
        cart.remove(prodId);
    }

    public void clear(){
        cart.clear();
    }
}
