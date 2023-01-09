package com.shopSpring.cart.converters;

import com.shopSpring.api.CartDto;
import com.shopSpring.api.CartItemDto;
import com.shopSpring.cart.model.Cart;
import com.shopSpring.cart.model.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;
    public CartDto entityToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setCart(cart.getCart().stream().map(item -> cartItemConverter.entityToDto(item)).collect(Collectors.toList()));
        return cartDto;
    }

}
