package com.shopSpring.demo.services;

import com.shopSpring.demo.model.Cart;
import com.shopSpring.demo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CartService {
    private Cart cart;
    private ProductService productService;

    @Autowired
    public void setProductRepository(ProductService productService) {
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
        System.out.println(this.cart);
    }

    public void remove(Long prodId){
        cart.remove(prodId);
    }

    public void clear(){
        cart.clear();
    }
}
