package com.shopSpring.core.controllers;

import com.shopSpring.core.entities.User;
import com.shopSpring.core.services.CartService;
import com.shopSpring.core.services.OrderService;
import com.shopSpring.core.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    UserService userService;
    OrderService orderService;
    CartService cartService;

    @PostMapping("/{login}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@PathVariable(name = "login") String login){
        User user = userService.findByUsername(login).orElseThrow(() -> new RuntimeException("User wasn't found login="+login));
        orderService.makeOrder(user.getId(), cartService.getCurrentCart());
    }
}
