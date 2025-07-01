package org.eternity.procedural.reservation.persistence.memory;

import org.eternity.procedural.reservation.domian.Movie;
import org.eternity.procedural.reservation.persistence.MovieDao;

public class MovieMemoryDao extends InMemoryDao<Movie> implements MovieDao {
    @Override
    public Movie selectMovie(Long movieId) {
        return findOne(movie -> movie.getId().equals(movieId));
    }
}
