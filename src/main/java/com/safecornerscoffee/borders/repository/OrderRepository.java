package com.safecornerscoffee.borders.repository;

import com.safecornerscoffee.borders.domain.Member;
import com.safecornerscoffee.borders.domain.order.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    public Order save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
        return order;
    }

    public Optional<Order> findById(Long id) {
        Order order = em.find(Order.class, id);
        return Optional.of(order);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    public List<Order> findByMember(Member member) {
        return em.createQuery("select o from Order o where o.member = :m", Order.class)
                .setParameter("m", member)
                .getResultList();
    }

    public void delete(Order order) {
        em.remove(order);
    }

}
