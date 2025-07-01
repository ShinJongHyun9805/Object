package org.eternity.procedural.reservation.persistence;

import org.eternity.procedural.reservation.domian.Reservation;

public interface ReservationDao {
    void insert(Reservation reservation);
}
