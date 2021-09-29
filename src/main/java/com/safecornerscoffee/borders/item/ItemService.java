package com.safecornerscoffee.borders.item;

import com.safecornerscoffee.borders.item.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Item save(Item item) {
        return repository.save(item);
    }

    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Item> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public void delete(Item item) {
        repository.delete(item);
    }
}
