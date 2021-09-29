package com.safecornerscoffee.borders.order;


import com.safecornerscoffee.borders.order.domain.Order;
import com.safecornerscoffee.borders.order.domain.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import static com.safecornerscoffee.borders.order.OrderSpecification.*;
import static org.springframework.data.jpa.domain.Specification.*;

public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Specification<Order> toSpecification() {
        return where(memberNameLike(memberName)).and(orderStatusEq(orderStatus));
    }
}
