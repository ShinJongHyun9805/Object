package org.eternity.procedural.reservation.persistence.memory;

import org.eternity.procedural.reservation.domian.DiscountCondition;
import org.eternity.procedural.reservation.persistence.DiscountConditionDao;

import java.util.List;

public class DiscountConditionMemoryDao extends InMemoryDao<DiscountCondition> implements DiscountConditionDao {
    @Override
    public List<DiscountCondition> selectDiscountConditions(Long policyId) {
        return findMany(condition -> condition.getPolicyId().equals(policyId));
    }
}