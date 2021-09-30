package com.safecornerscoffee.borders.data;

import javax.persistence.Embeddable;

@Embeddable
public class Money {
    private Integer value;

    protected Money() {}

    protected Money(Integer value) {
        this.value = value;
    }

    public static Money of(Integer value) {
        return new Money(value);
    }

    public Integer getValue() {
        return value;
    }

    protected void setValue(Integer value) {
        this.value = value;
    }
}
