package com.safecornerscoffee.borders.item.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super(String.format("Can not find Item{id=%d}", id));
    }
}
