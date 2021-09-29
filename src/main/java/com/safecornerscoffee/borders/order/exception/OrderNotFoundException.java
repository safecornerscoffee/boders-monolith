package com.safecornerscoffee.borders.order.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super(String.format("Can not find Order{id=%d}", id));
    }
}
