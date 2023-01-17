package com.shopSpring.cart.converters;

import com.shopSpring.api.CartItemDto;
import com.shopSpring.cart.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setTitle(cartItem.getTitle());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setProdId(cartItem.getProdId());
        cartItemDto.setTotalPrice(cartItem.getTotalPrice());
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }
}
