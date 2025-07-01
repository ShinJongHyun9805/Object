package org.eternity.procedural;

import org.eternity.procedural.generic.Money;
import org.eternity.procedural.reservation.domian.*;
import org.eternity.procedural.reservation.persistence.*;
import org.eternity.procedural.reservation.persistence.memory.*;
import org.eternity.procedural.reservation.service.ReservationService;


import java.time.LocalDateTime;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.LocalTime.of;
import static org.eternity.procedural.reservation.domian.DiscountCondition.ConditionType.PERIOD_CONDITION;
import static org.eternity.procedural.reservation.domian.DiscountCondition.ConditionType.SEQUENCE_CONDITION;
import static org.eternity.procedural.reservation.domian.DiscountPolicy.PolicyType.AMOUNT_POLICY;

public class Main {

    private ScreeningDao screeningDAO = new ScreeningMemoryDao();
    private MovieDao movieDAO = new MovieMemoryDao();
    private DiscountPolicyDao discountPolicyDAO = new DiscountPolicyMemoryDao();
    private DiscountConditionDao discountConditionDAO = new DiscountConditionMemoryDao();
    private ReservationDao reservationDAO = new ReservationMemoryDao();


    ReservationService reservationService = new ReservationService(
            screeningDAO,
            movieDAO,
            discountPolicyDAO,
            discountConditionDAO,
            reservationDAO);

    private Screening initializeData() {
        Movie movie = new Movie("한산", 150, Money.wons(10000));
        movieDAO.insert(movie);

        DiscountPolicy discountPolicy = new DiscountPolicy(movie.getId(), AMOUNT_POLICY, Money.wons(1000), null);
        discountPolicyDAO.insert(discountPolicy);

        discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), SEQUENCE_CONDITION, null, null, null, 1));
        discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), SEQUENCE_CONDITION, null, null, null, 10));
        discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), PERIOD_CONDITION, MONDAY, null, null, null));
        discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), PERIOD_CONDITION, WEDNESDAY, null, null, null));

        Screening screening = new Screening(movie.getId(), 7, LocalDateTime.of(2024, 12, 11, 18, 0));
        screeningDAO.insert(screening);

        return screening;
    }

    public void run() {
        Screening screening = initializeData();

        Reservation reservation = reservationService.reserveScreening(1L, screening.getId(), 2);

        System.out.printf("관객수 : %d, 요금: %s%n", reservation.getAudienceCount(), reservation.getFee());
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
