package com.shopSpring.demo.services;

import com.shopSpring.demo.model.Cart;
import com.shopSpring.demo.entities.Order;
import com.shopSpring.demo.entities.OrderItem;
import com.shopSpring.demo.entities.User;
import com.shopSpring.demo.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    @Transactional
    public void makeOrder(Long userId, Cart cart){
        User user = userService.getById(userId).orElseThrow(() -> new RuntimeException("User not found id = " + userId));
        Order order = new Order();
        order.setUser(user);
        List<OrderItem> orderItems = cart.getCart().stream().map(i ->
                new OrderItem(i.getQuantity(),
                        productService.findById(i.getProdId()).orElseThrow(() ->
                                new RuntimeException("Product not found id = " + i.getProdId())), order)).
                collect(Collectors.toList());
        order.setOrderItems(orderItems);
        order.setStatus("Success");
        for (OrderItem i:orderItems) {
            i.setOrder(order);
        }
        System.out.println(order.getOrderItems());
        orderRepository.save(order);
    }
}
