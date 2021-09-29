package com.safecornerscoffee.borders.member;

import com.safecornerscoffee.borders.member.exception.MemberDuplicatedException;
import com.safecornerscoffee.borders.member.exception.MemberNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class MemberAdvice {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handleNotFoundError(Exception e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MemberDuplicatedException.class)
    public ResponseEntity<?> handleDuplcatedError(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
