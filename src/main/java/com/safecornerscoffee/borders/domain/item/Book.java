package com.safecornerscoffee.borders.domain.item;

import javax.persistence.Entity;

@Entity
public class Book extends Item {

    private String author;
    private String isbn;
}
