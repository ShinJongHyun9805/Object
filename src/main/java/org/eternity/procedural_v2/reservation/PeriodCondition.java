package org.eternity.procedural_v2.reservation;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
                !startTime.isAfter(screening.getStartTime().toLocalTime()) &&
                !endTime.isBefore(screening.getStartTime().toLocalTime());

    }
}
