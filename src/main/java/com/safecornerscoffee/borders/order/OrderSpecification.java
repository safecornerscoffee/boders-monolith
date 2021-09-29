package com.safecornerscoffee.borders.order;

import com.safecornerscoffee.borders.member.Member;
import com.safecornerscoffee.borders.order.domain.Order;
import com.safecornerscoffee.borders.order.domain.OrderStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class OrderSpecification {

    public static Specification<Order> memberNameLike(final String memberName) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (!StringUtils.hasText(memberName)) return null;

                Join<Order, Member> m = root.join("member", JoinType.INNER);
                return criteriaBuilder.equal(m.<String>get("name"), "%" + memberName + "%");
            }
        };
    }

    public static Specification<Order> orderStatusEq(final OrderStatus orderStatus) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                if ( orderStatus == null) return null;

                return criteriaBuilder.equal(root.get("orderStatus"), orderStatus);
            }
        };
    }
}
