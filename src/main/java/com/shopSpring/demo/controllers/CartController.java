package com.shopSpring.demo.controllers;

import com.shopSpring.demo.model.Cart;
import com.shopSpring.demo.services.CartService;
import com.shopSpring.demo.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;
    private OrderService orderService;

    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable(name = "id") Long id){
        cartService.add(id);
    }

    @GetMapping
    public Cart getCart(){
        return cartService.getCurrentCart();
    }

    @GetMapping("/order/{id}")
    public void makeOrder(@PathVariable(name = "id") Long id){
        orderService.makeOrder(id, cartService.getCurrentCart());
    }

    @GetMapping("/clear")
    public void clear(){
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable(name = "id") Long id){
        cartService.remove(id);
    }
}
