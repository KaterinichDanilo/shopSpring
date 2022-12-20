package com.shopSpring.core.integrations;

import com.shopSpring.api.CartDto;
import com.shopSpring.api.ProductDto;
import com.shopSpring.api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCart(){
        CartDto cartDto = cartServiceWebClient.get()
                .uri("api/v1/cart")
                .retrieve()
                .bodyToMono(CartDto.class).block();
        return cartDto;
    }

    public void clear(){
        cartServiceWebClient.get()
                .uri("api/v1/cart/clear")
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
