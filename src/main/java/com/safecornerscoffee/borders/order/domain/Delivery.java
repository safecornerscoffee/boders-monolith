package com.safecornerscoffee.borders.order.domain;

import com.safecornerscoffee.borders.data.Address;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    protected Delivery() {}

    public Delivery(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Address getAddress() {
        return address;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
