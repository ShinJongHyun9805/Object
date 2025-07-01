package org.eternity.procedural_v1.reservation.persistence;

import org.eternity.procedural_v1.reservation.domian.Reservation;

public interface ReservationDao {
    void insert(Reservation reservation);
}
