package com.safecornerscoffee.borders.catalogue.category;

import com.safecornerscoffee.borders.item.domain.Item;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
    joinColumns = @JoinColumn(name = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> child = new HashSet<>();

    public void addChildCategory(Category category) {
        child.add(category);
        category.setParent(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Category getParent() {
        return parent;
    }

    public Set<Category> getChild() {
        return child;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void setChild(Set<Category> child) {
        this.child = child;
    }
}
