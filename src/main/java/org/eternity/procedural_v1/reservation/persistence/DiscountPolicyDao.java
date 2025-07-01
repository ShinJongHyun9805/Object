package org.eternity.procedural_v1.reservation.persistence;

import org.eternity.procedural_v1.reservation.domian.DiscountPolicy;

public interface DiscountPolicyDao {
    DiscountPolicy selectDiscountPolicy(Long movieId);

    void insert(DiscountPolicy discountPolicy);
}
