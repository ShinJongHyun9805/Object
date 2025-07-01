package org.eternity.procedural.reservation.persistence.memory;

import org.eternity.procedural.reservation.domian.Reservation;
import org.eternity.procedural.reservation.persistence.ReservationDao;

public class ReservationMemoryDao extends InMemoryDao<Reservation> implements ReservationDao {
    @Override
    public void insert(Reservation reservation) {
        super.insert(reservation);
    }
}
