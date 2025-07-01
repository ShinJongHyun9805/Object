package org.eternity.procedural.reservation.persistence;

import org.eternity.procedural.reservation.domian.DiscountCondition;

import java.util.List;

public interface DiscountConditionDao {
    List<DiscountCondition> selectDiscountConditions(Long policyId);

    void insert(DiscountCondition discountCondition);
}
