package org.eternity.procedural_v1.reservation.service;

import org.eternity.procedural_v1.generic.Money;
import org.eternity.procedural_v1.reservation.domian.*;
import org.eternity.procedural_v1.reservation.persistence.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.eternity.procedural_v1.reservation.domian.DiscountCondition.ConditionType.PERIOD_CONDITION;
import static org.eternity.procedural_v1.reservation.domian.DiscountCondition.ConditionType.SEQUENCE_CONDITION;
import static org.eternity.procedural_v1.reservation.domian.DiscountPolicy.PolicyType.AMOUNT_POLICY;
import static org.eternity.procedural_v1.reservation.domian.DiscountPolicy.PolicyType.PERCENT_POLICY;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ScreeningDao screeningDAO;
    @Mock private MovieDao movieDAO;
    @Mock private DiscountPolicyDao discountPolicyDAO;
    @Mock private DiscountConditionDao discountConditionDAO;
    @Mock private ReservationDao reservationDAO;

    @Test
    public void 금액할인정책_계산() {
        // given
        Long customerId = 1L;
        Long screeningId = 1L;
        Long movieId = 1L;
        Long policyId = 1L;

        Mockito.when(screeningDAO.selectScreening(screeningId))
                .thenReturn(new Screening(screeningId, movieId, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));

        Mockito.when(movieDAO.selectMovie(movieId))
                .thenReturn(new Movie(movieId, "한신", 120, Money.wons(10000)));

        Mockito.when(discountPolicyDAO.selectDiscountPolicy(movieId))
                .thenReturn(
                        new DiscountPolicy(
                                policyId,
                                movieId,
                                AMOUNT_POLICY,
                                Money.wons(1000),
                                null,
                                List.of(
                                        new DiscountCondition(1L, policyId, SEQUENCE_CONDITION, null, null, 1),
                                        new DiscountCondition(2L, policyId, SEQUENCE_CONDITION, null, null, 10),
                                        new DiscountCondition(3L, policyId, PERIOD_CONDITION, MONDAY, TimeInterval.of(LocalTime.of(10, 12), LocalTime.of(12, 0)), null),
                                        new DiscountCondition(4L, policyId, PERIOD_CONDITION, WEDNESDAY, TimeInterval.of(LocalTime.of(18, 0), LocalTime.of(21, 0)), null))));

        // when
        Reservation reservation = reservationService.reserveScreening(customerId, screeningId, 2);

        // then
        Assertions.assertEquals(reservation.getFee(), Money.wons(18000));
    }

    @Test
    public void 비율할인정책_계산() {
        // given
        Long customerId = 1L;
        Long screeningId = 1L;
        Long movieId = 1L;
        Long policyId = 1L;

        Mockito.when(screeningDAO.selectScreening(screeningId))
                .thenReturn(new Screening(screeningId, movieId, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));

        Mockito.when(movieDAO.selectMovie(movieId))
                .thenReturn(new Movie(movieId, "한신", 120, Money.wons(10000)));

        Mockito.when(discountPolicyDAO.selectDiscountPolicy(movieId))
                .thenReturn(
                        new DiscountPolicy(
                                policyId,
                                movieId,
                                PERCENT_POLICY,
                                null,
                                0.1,
                                List.of(
                                        new DiscountCondition(1L, policyId, SEQUENCE_CONDITION, null, null, 1),
                                        new DiscountCondition(2L, policyId, SEQUENCE_CONDITION, null, null, 10),
                                        new DiscountCondition(3L, policyId, PERIOD_CONDITION, MONDAY, TimeInterval.of(LocalTime.of(10, 12), LocalTime.of(12, 0)), null),
                                        new DiscountCondition(4L, policyId, PERIOD_CONDITION, WEDNESDAY, TimeInterval.of(LocalTime.of(18, 0), LocalTime.of(21, 0)), null))));

        // when
        Reservation reservation = reservationService.reserveScreening(customerId, screeningId, 2);

        // then
        Assertions.assertEquals(reservation.getFee(), Money.wons(18000));
    }
}