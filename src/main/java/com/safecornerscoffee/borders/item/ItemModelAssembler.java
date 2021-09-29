package com.safecornerscoffee.borders.item;

import com.safecornerscoffee.borders.item.domain.Item;
import org.hibernate.EntityMode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<Item, EntityModel<Item>> {

    @Override
    public EntityModel<Item> toModel(Item item) {
        return EntityModel.of(item,
                linkTo(methodOn(ItemController.class).one(item.getId())).withSelfRel(),
                linkTo(methodOn(ItemController.class).all()).withRel("items"));
    }
}
