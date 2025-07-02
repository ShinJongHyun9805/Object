package org.eternity.procedural_v2.reservation;

import org.eternity.procedural_v2.generic.Money;

public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getFixedFee().times(percent);
    }
}
