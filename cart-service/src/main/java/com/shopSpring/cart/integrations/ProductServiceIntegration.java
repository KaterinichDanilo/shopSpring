package com.shopSpring.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.shopSpring.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/shop/api/v1/products/" + id, ProductDto.class));
    }
}
