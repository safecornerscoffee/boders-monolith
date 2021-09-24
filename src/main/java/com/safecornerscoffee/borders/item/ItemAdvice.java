package com.safecornerscoffee.borders.item;

import com.safecornerscoffee.borders.item.exception.ItemNotFoundException;
import com.safecornerscoffee.borders.item.exception.ItemOutOfStockException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemAdvice {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ItemOutOfStockException.class)
    public ResponseEntity<?> handleOutOfStockException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
