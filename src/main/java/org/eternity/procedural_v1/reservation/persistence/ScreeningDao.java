package org.eternity.procedural_v1.reservation.persistence;

import org.eternity.procedural_v1.reservation.domian.Screening;

public interface ScreeningDao {
    Screening selectScreening(Long screeningId);

    void insert(Screening screening);
}
