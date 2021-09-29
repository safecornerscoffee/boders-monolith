package com.safecornerscoffee.borders.order;

import com.safecornerscoffee.borders.item.ItemService;
import com.safecornerscoffee.borders.item.domain.Item;
import com.safecornerscoffee.borders.item.exception.ItemNotFoundException;
import com.safecornerscoffee.borders.member.Member;
import com.safecornerscoffee.borders.member.MemberRepository;
import com.safecornerscoffee.borders.member.exception.MemberNotFoundException;
import com.safecornerscoffee.borders.order.domain.Delivery;
import com.safecornerscoffee.borders.order.domain.Order;
import com.safecornerscoffee.borders.order.domain.OrderItem;
import com.safecornerscoffee.borders.order.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemService itemService;

    public OrderService(OrderRepository orderRepository, MemberRepository memberRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.itemService = itemService;
    }

    @Transactional
    public Order order (Long memberId, Long itemId, int count) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        Item item = itemService.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));

        Delivery delivery = new Delivery(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        List<OrderItem> orderItems = Arrays.asList(orderItem);

        Order order = Order.createOrder(member, delivery, orderItems);

        orderRepository.save(order);
        return order;
    }

    @Transactional
    public void cancel(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        order.cancel();
    }

    public List<Order> findAll(OrderSpecification orderSpecification) {
        return orderRepository.findAll(orderSpecification);
    }

    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByMember(Member member) {
        return orderRepository.findByMember(member);
    }

    @Transactional
    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
