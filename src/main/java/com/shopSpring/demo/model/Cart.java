package com.shopSpring.demo.model;

import com.shopSpring.demo.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
public class Cart {
    private List<CartItem> cart;
    private int totalPrice;

    public Cart() {
        this.cart = new ArrayList<>();
    }

    public List<CartItem> getCart() {
        return Collections.unmodifiableList(cart);
    }

    private void recalculatePrice(){
        this.totalPrice = 0;
        for (CartItem item : this.cart) {
            this.totalPrice += item.getTotalPrice();
        }
    }

    public void add(Product product){
        for (CartItem item : cart) {
            if (item.getProdId().equals(product.getId())){
                item.changeQuantity(1L);
                return;
            }
        }
    }

    public void remove(Long id){
        if(cart.removeIf(cartItem -> cartItem.getProdId().equals(id))) recalculatePrice();
    }

    public void clear(){
        cart.clear();
        totalPrice = 0;
    }
}
