package com.safecornerscoffee.borders.item;

import com.safecornerscoffee.borders.item.domain.Item;
import com.safecornerscoffee.borders.item.exception.ItemNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ItemController {

    private final ItemService service;
    private final ItemModelAssembler assembler;

    public ItemController(ItemService service, ItemModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }


    @GetMapping("/items")
    public CollectionModel<EntityModel<Item>> all() {
        List<EntityModel<Item>> collect = service.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(collect,
                linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }

    @PostMapping("/items")
    public EntityModel<Item> create(@RequestBody Item item) {
        service.save(item);
        return assembler.toModel(item);
    }

    @GetMapping("/items/{id}")
    public EntityModel<Item> one(@PathVariable Long id) {
        Item item = service.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(item);
    }

    @PutMapping("/items/{id}")
    public EntityModel<Item> update(@RequestBody Item item, @PathVariable Long id) {
        Item updatedItem = service.findById(id).map(i -> {
            i.setName(item.getName());
            i.setStockQuantity(item.getStockQuantity());
            i.setPrice(item.getPrice());
            return service.save(i);
        }).orElseGet(() -> {
            item.setId(id);
            return service.save(item);
        });

        return assembler.toModel(updatedItem);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Item item = service.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        service.delete(item);
        return ResponseEntity.noContent().build();
    }
}
