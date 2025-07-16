package org.eternity.procedural_v2.reservation;

import org.eternity.procedural_v2.generic.Money;

import java.util.List;


public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions;

    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = List.of(conditions);
    }

    public Money calculateDiscount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return Money.ZERO;
    }

    protected abstract Money getDiscountAmount(Screening screening);
}
