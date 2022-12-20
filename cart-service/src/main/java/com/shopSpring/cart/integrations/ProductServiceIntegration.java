package com.shopSpring.cart.integrations;

import com.shopSpring.api.ResourceNotFoundException;
import com.shopSpring.cart.properties.ProductServiceIntegrationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.shopSpring.api.ProductDto;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public Optional<ProductDto> getProductById(Long id){
        ProductDto productDto = productServiceWebClient.get()
                .uri("api/v1/products/" + id)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Product wasn't found id="+id)))
                .bodyToMono(ProductDto.class).block();
        return Optional.ofNullable(productDto);
    }
}
