package org.eternity.procedural_v1.reservation.persistence.memory;

import org.eternity.procedural_v1.reservation.domian.Reservation;
import org.eternity.procedural_v1.reservation.persistence.ReservationDao;

public class ReservationMemoryDao extends InMemoryDao<Reservation> implements ReservationDao {
    @Override
    public void insert(Reservation reservation) {
        super.insert(reservation);
    }
}
