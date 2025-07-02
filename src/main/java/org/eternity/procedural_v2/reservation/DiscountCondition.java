package org.eternity.procedural_v2.reservation;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
