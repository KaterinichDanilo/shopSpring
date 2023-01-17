package com.shopSpring.core.controllers;

import com.shopSpring.core.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    OrderService orderService;
    @PostMapping("/{login}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String login){
        orderService.makeOrder(login);
    }
}
