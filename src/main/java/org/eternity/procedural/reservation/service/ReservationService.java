package org.eternity.procedural.reservation.service;

import org.eternity.procedural.generic.Money;
import org.eternity.procedural.reservation.domian.*;
import org.eternity.procedural.reservation.persistence.*;

import java.util.List;

public class ReservationService {

    private ScreeningDao screeningDAO;
    private MovieDao movieDAO;
    private DiscountPolicyDao discountPolicyDAO;
    private DiscountConditionDao discountConditionDAO;
    private ReservationDao reservationDAO;

    public ReservationService(ScreeningDao screeningDAO,
                              MovieDao movieDAO,
                              DiscountPolicyDao discountPolicyDAO,
                              DiscountConditionDao discountConditionDAO,
                              ReservationDao reservationDAO) {
        this.screeningDAO = screeningDAO;
        this.movieDAO = movieDAO;
        this.discountConditionDAO = discountConditionDAO;
        this.discountPolicyDAO = discountPolicyDAO;
        this.reservationDAO = reservationDAO;
    }

    public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
        Screening screening = screeningDAO.selectScreening(screeningId);
        Movie movie = movieDAO.selectMovie(screening.getMovieId());
        DiscountPolicy policy = discountPolicyDAO.selectDiscountPolicy(movie.getId());

        boolean discountCondition = policy.findDiscountCondition(screening);

        Money fee;
        if (discountCondition) {
            fee = movie.getFee().minus(policy.calculateDiscount(policy, movie));
        } else {
            fee = movie.getFee();
        }

        Reservation reservation = makeReservation(customerId, screeningId, audienceCount, fee);
        reservationDAO.insert(reservation);

        return reservation;
    }

    private Reservation makeReservation(Long customerId, Long screeningId, Integer audienceCount, Money fee) {
        return new Reservation(customerId, screeningId, audienceCount, fee.times(audienceCount));
    }
}
