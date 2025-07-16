package org.eternity.procedural_v2.reservation;

import org.eternity.procedural_v2.generic.Money;

public class NoneDiscountPolicy extends DiscountPolicy{
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
