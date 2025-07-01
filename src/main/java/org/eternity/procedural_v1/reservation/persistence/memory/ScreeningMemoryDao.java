package org.eternity.procedural_v1.reservation.persistence.memory;

import org.eternity.procedural_v1.reservation.domian.Screening;
import org.eternity.procedural_v1.reservation.persistence.ScreeningDao;

public class ScreeningMemoryDao extends InMemoryDao<Screening> implements ScreeningDao {
    @Override
    public Screening selectScreening(Long id) {
        return findOne(screening -> screening.getId().equals(id));
    }

}
