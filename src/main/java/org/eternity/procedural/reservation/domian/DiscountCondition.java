package org.eternity.procedural.reservation.domian;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class DiscountCondition {
    public enum ConditionType {
        PERIOD_CONDITION,   // 기간
        SEQUENCE_CONDITION, // 시퀀스
        COMBINED_CONDITION  // 조합 조건
    }

    private Long id;
    private Long policyId;
    private ConditionType conditionType;
    private DayOfWeek dayOfWeek;

    private TimeInterval timeInterval;

    private Integer sequence;

    public Long getPolicyId() {
        return policyId;
    }

    public DiscountCondition() {}

    public DiscountCondition(Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, Integer sequence) {
        this(null, policyId, conditionType, dayOfWeek, TimeInterval.of(startTime, endTime), sequence);
    }

    public DiscountCondition(Long id, Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek, TimeInterval timeInterval, Integer sequence) {
        this.id = id;
        this.policyId = policyId;
        this.conditionType = conditionType;
        this.dayOfWeek = dayOfWeek;
        this.timeInterval = timeInterval;
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening) {
        if (isPeriodCondition()) {
            if (screening.isPlayedIn(
                    dayOfWeek,
                    timeInterval.getStartTime(),
                    timeInterval.getEndTime())) {

                return true;
            }
        } else if (isSequenceCondition()) {
            if (sequence.equals(screening.getSequence())) {
                return true;
            }
        } else if (isCombinedCondition()) {
            if (
                    (sequence.equals(screening.getSequence())) &&
                            (screening.isPlayedIn(
                                    dayOfWeek,
                                    timeInterval.getStartTime(),
                                    timeInterval.getEndTime()))) {

                return true;
            }
        }

        return false;
    }

    private boolean isPeriodCondition() {
        return ConditionType.PERIOD_CONDITION.equals(conditionType);
    }

    private boolean isSequenceCondition() {
        return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
    }

    private boolean isCombinedCondition() {
        return ConditionType.COMBINED_CONDITION.equals(conditionType);
    }

}