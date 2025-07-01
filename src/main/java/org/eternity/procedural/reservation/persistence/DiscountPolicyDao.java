package org.eternity.procedural.reservation.persistence;

import org.eternity.procedural.reservation.domian.DiscountPolicy;

public interface DiscountPolicyDao {
    DiscountPolicy selectDiscountPolicy(Long movieId);

    void insert(DiscountPolicy discountPolicy);
}
