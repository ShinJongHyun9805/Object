package org.eternity.procedural_v1.reservation.persistence;

import org.eternity.procedural_v1.reservation.domian.DiscountCondition;

import java.util.List;

public interface DiscountConditionDao {
    List<DiscountCondition> selectDiscountConditions(Long policyId);

    void insert(DiscountCondition discountCondition);
}
