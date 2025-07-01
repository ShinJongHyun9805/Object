package org.eternity.procedural_v1.reservation.persistence;

import org.eternity.procedural_v1.reservation.domian.Movie;

public interface MovieDao {

    Movie selectMovie(Long movieId);

    void insert(Movie movie);
}
