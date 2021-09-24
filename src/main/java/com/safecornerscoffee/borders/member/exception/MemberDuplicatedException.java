package com.safecornerscoffee.borders.member.exception;

public class MemberDuplicatedException extends RuntimeException {

    public MemberDuplicatedException(Long id) {
        super(String.format("Duplicate Member{id=%d}", id));
    }
}
