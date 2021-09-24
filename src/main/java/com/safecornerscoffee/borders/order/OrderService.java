package com.safecornerscoffee.borders.order;

import com.safecornerscoffee.borders.member.Member;
import com.safecornerscoffee.borders.order.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Order save(Order order) {
        return repository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public List<Order> findByMember(Member member) {
        return repository.findByMember(member);
    }

    @Transactional
    public void delete(Order order) {
        repository.delete(order);
    }
}
