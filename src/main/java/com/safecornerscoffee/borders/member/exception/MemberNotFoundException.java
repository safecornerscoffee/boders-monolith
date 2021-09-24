package com.safecornerscoffee.borders.member.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long id) {
        super(String.format("Can not find Member{id=%d}", id));
    }
}
