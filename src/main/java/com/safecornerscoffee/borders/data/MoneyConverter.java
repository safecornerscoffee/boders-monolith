package com.safecornerscoffee.borders.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money money) {
        if (money == null) {
            return null;
        }

        return money.getValue();
    }

    @Override
    public Money convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        return Money.of(integer);
    }
}
