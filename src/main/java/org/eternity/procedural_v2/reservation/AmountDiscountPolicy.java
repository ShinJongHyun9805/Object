package org.eternity.procedural_v2.reservation;

import org.eternity.procedural_v2.generic.Money;

/**********************************************
 * ****************금액 할인 정책 ****************
 * *********************************************/
public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
