package org.eternity.procedural.reservation.persistence;

import org.eternity.procedural.reservation.domian.Movie;

public interface MovieDao {

    Movie selectMovie(Long movieId);

    void insert(Movie movie);
}
