package com.shopSpring.core.services;

import com.shopSpring.api.ResourceNotFoundException;
import com.shopSpring.core.model.Cart;
import com.shopSpring.core.entities.Order;
import com.shopSpring.core.entities.OrderItem;
import com.shopSpring.core.entities.User;
import com.shopSpring.core.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final com.shopSpring.core.services.ProductService productService;
    private final com.shopSpring.core.services.UserService userService;

    @Transactional
    public void makeOrder(Long userId, Cart cart){
        User user = userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found id = " + userId));
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
        orderRepository.save(order);
    }
}
