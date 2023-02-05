package com.shopSpring.cart.model;

public enum Command {
    COMMAND_MAKEORDER("orderServiceMakeOrderIml"),
    COMMAND_ADDPRODUCT("productServiceAddOrderIml"),
    COMMAND_SAVEPRODUCT("productServiceSaveOrderIml"),
    COMMAND_ADDUSER("userServiceAddUserIml");

    private final String bean;

    Command(String bean) {
        this.bean = bean;
    }
}
