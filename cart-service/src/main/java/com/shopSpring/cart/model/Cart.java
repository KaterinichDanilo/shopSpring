package com.shopSpring.cart.model;

import com.shopSpring.api.ProductDto;
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

    public void add(ProductDto product){
        for (CartItem item : cart) {
            if (item.getProdId().equals(product.getId())){
                item.changeQuantity(1);
                recalculatePrice();
                return;
            }
        }

        cart.add(new CartItem(product.getId(), product.getTitle(), 1,
                product.getPrice(), product.getPrice()));
        recalculatePrice();
    }

    public void increase(Long prodId, int delta){
        CartItem cartItem = cart.stream().filter(i -> i.getProdId().equals(prodId)).findAny().
                orElseThrow(() -> new RuntimeException("Product wasn't found in cart id = " + prodId));
        cartItem.changeQuantity(delta);
        recalculatePrice();
    }

    public void reduce(Long prodId, int delta){
        CartItem cartItem = cart.stream().filter(i -> i.getProdId().equals(prodId)).findAny().
                orElseThrow(() -> new RuntimeException("Product wasn't found in cart id = " + prodId));
        if (cartItem.getQuantity() <= Math.abs(delta)){
            remove(cartItem.getProdId());
            recalculatePrice();
            return;
        }
        cartItem.changeQuantity(delta);
        recalculatePrice();
    }

    public void remove(Long id){
        if(cart.removeIf(cartItem -> cartItem.getProdId().equals(id))) recalculatePrice();
    }

    public void clear(){
        cart.clear();
        totalPrice = 0;
    }
}
