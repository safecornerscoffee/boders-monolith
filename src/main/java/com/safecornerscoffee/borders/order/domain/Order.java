package com.safecornerscoffee.borders.order.domain;



import com.safecornerscoffee.borders.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


    protected Order() {}

    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        orderItems.forEach(order::addOrderItem);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ORDER);
        return order;
    }

    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMPLETE) {
            throw new RuntimeException("Could not cancel order at the " + getDelivery() + "delivery stage");
        }

        this.setOrderStatus(OrderStatus.CANCEL);
        orderItems.forEach(OrderItem::cancel);
    }

    public void complete() {

    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public int getTotalPrice() {
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
