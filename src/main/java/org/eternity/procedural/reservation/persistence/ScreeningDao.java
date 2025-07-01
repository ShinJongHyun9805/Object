package org.eternity.procedural.reservation.persistence;

import org.eternity.procedural.reservation.domian.Screening;

public interface ScreeningDao {
    Screening selectScreening(Long screeningId);

    void insert(Screening screening);
}
