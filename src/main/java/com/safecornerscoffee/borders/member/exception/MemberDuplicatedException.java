package com.safecornerscoffee.borders.member.exception;

public class MemberDuplicatedException extends RuntimeException {

    public MemberDuplicatedException(String username) {
        super(String.format("Duplicate username{id=%s}", username));
    }
}
