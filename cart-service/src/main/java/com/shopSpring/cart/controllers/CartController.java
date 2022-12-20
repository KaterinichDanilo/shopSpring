package com.shopSpring.cart.controllers;

import com.shopSpring.api.CartDto;
import com.shopSpring.cart.converters.CartConverter;
import com.shopSpring.cart.model.Cart;
import com.shopSpring.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;
    private CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable(name = "id") Long id){
        cartService.add(id);
    }

    @GetMapping
    public CartDto getCart(){
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/inc/{id}")
    public void increase(@PathVariable(name = "id") Long id){
        cartService.getCurrentCart().increase(id, 1);
    }

    @GetMapping("/reduce/{id}")
    public void reduce(@PathVariable(name = "id") Long id){
        cartService.getCurrentCart().reduce(id, -1);
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
