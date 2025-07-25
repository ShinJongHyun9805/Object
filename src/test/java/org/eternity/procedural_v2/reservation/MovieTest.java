package org.eternity.procedural_v2.reservation;

import org.eternity.procedural_v2.generic.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void 비율할인정책_계산() {
        Movie movie = new Movie(
                Money.wons(10000)
                , new PercentDiscountPolicy(
                        0.1
                        , new SequenceCondition(1)
                        , new SequenceCondition(3)
                        , new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 10))
                )
        );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2025, 7, 16, 18, 0));

        Money fee = movie.calculateFee(screening);
        Assertions.assertEquals(fee, Money.wons(9000));

    }

    @Test
    void 금액할인정책_계산() {
        Movie movie = new Movie(
                Money.wons(10000)
                , new AmountDiscountPolicy(
                        Money.wons(1000)
                        , new SequenceCondition(1)
                        , new PeriodCondition(DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(21, 10))
                        , new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 10))
        )
        );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2025, 7, 16, 18, 0));

        Money fee = movie.calculateFee(screening);
        Assertions.assertEquals(fee, Money.wons(9000));

    }

    @Test
    void 비할인정책_계산() {
        Movie movie = new Movie(
                Money.wons(10000)
                , new NoneDiscountPolicy()
        );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2025, 7, 16, 18, 0));

        Money fee = movie.calculateFee(screening);
        Assertions.assertEquals(fee, Money.wons(10000));

    }
}