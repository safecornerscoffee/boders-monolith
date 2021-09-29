package com.safecornerscoffee.borders.order;

import com.safecornerscoffee.borders.member.Member;
import com.safecornerscoffee.borders.order.domain.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    public List<Order> findAll(OrderSpecification orderSpecification) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);

        List<Predicate> criteria = new ArrayList<Predicate>();

        if (orderSpecification.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("orderStatus"), orderSpecification.getOrderStatus());
            criteria.add(status);
        }

        if (StringUtils.hasText(orderSpecification.getMemberName())) {
            Join<Order, Member> m = o.join("member", JoinType.INNER);
            Predicate name = cb.like(m.<String>get("name"), "%"+orderSpecification.getMemberName()+"%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);

        return query.getResultList();
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
