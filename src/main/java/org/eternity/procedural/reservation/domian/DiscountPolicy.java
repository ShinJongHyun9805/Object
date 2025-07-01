package org.eternity.procedural.reservation.domian;


import lombok.Data;
import org.eternity.procedural.generic.Money;

import java.util.ArrayList;
import java.util.List;

@Data
public class DiscountPolicy {
    public enum PolicyType { PERCENT_POLICY, AMOUNT_POLICY }

    private Long id;
    private Long movieId;
    private PolicyType policyType;
    private Money amount;
    private Double percent;
    private List<DiscountCondition> conditions;

    public DiscountPolicy() {
    }

    public DiscountPolicy(Long movieId, PolicyType policyType, Money amount, Double percent) {
        this(null, movieId, policyType, amount, percent, new ArrayList<>());
    }

    public DiscountPolicy(Long id, Long movieId, PolicyType policyType, Money amount, Double percent, List<DiscountCondition> conditions) {
        this.id = id;
        this.movieId = movieId;
        this.policyType = policyType;
        this.amount = amount;
        this.percent = percent;
        this.conditions = conditions;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    private boolean isAmountPolicy() {
        return PolicyType.AMOUNT_POLICY.equals(policyType);
    }

    private boolean isPercentPolicy() {
        return PolicyType.PERCENT_POLICY.equals(policyType);
    }

    public boolean findDiscountCondition(Screening screening) {
        for (DiscountCondition condition : conditions) {
            return condition.isSatisfiedBy(screening);
        }

        return false;
    }

    public Money calculateDiscount(DiscountPolicy policy, Movie movie) {
        if (policy.isAmountPolicy()) {
            return amount;
        } else if (policy.isPercentPolicy()) {
            return movie.getFee().times(percent);
        }

        return Money.ZERO;
    }
}
