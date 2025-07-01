package org.eternity.procedural_v1.reservation.persistence.memory;

import org.eternity.procedural_v1.reservation.domian.DiscountPolicy;
import org.eternity.procedural_v1.reservation.persistence.DiscountPolicyDao;

public class DiscountPolicyMemoryDao extends InMemoryDao<DiscountPolicy> implements DiscountPolicyDao {
    @Override
    public DiscountPolicy selectDiscountPolicy(Long movieId) {
        return findOne(policy -> policy.getMovieId().equals(movieId));
    }
}
