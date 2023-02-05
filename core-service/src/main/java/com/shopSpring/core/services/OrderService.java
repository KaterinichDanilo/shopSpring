package com.shopSpring.core.services;

import com.shopSpring.api.CartDto;
import com.shopSpring.core.entities.Order;
import com.shopSpring.core.entities.OrderItem;
import com.shopSpring.core.integrations.CartServiceIntegration;
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
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void makeOrder(String login){
        Order order = new Order();
        CartDto cart = cartServiceIntegration.getCart();
        order.setUsername(login);
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
