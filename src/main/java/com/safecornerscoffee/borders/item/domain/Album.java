package com.safecornerscoffee.borders.item.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item {

    private String artist;
    private String etc;
}
