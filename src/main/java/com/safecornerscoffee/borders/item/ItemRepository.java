package com.safecornerscoffee.borders.item;

import com.safecornerscoffee.borders.item.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager em;

    public Item save(Item item) {

        if (item.getId() == null ) {
            em.persist(item);
        } else {
            em.merge(item);
        }
        return item;
    }

    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.of(item);
    }

    public Optional<Item> findByName(String name) {
        Item item = em.createQuery("select i from Item i where i.name = :name", Item.class)
                .setParameter("name", name)
                .getSingleResult();

        return Optional.of(item);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    public void delete(Item item) {
        em.remove(item);
    }
}
