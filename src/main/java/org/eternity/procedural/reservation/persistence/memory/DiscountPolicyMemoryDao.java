package org.eternity.procedural.reservation.persistence.memory;

import org.eternity.procedural.reservation.domian.DiscountPolicy;
import org.eternity.procedural.reservation.persistence.DiscountPolicyDao;

public class DiscountPolicyMemoryDao extends InMemoryDao<DiscountPolicy> implements DiscountPolicyDao {
    @Override
    public DiscountPolicy selectDiscountPolicy(Long movieId) {
        return findOne(policy -> policy.getMovieId().equals(movieId));
    }
}
