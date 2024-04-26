package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    List<MovieEntity> getAll();

    ResponseEntity deleteMovie(int id);
}
