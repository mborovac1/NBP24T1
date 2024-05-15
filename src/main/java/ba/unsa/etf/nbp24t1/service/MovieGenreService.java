package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieGenreEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieGenreService {
    List<MovieGenreEntity> getAll();

    ResponseEntity createMovieGenre(List<MovieGenreEntity> movieGenres);
}
