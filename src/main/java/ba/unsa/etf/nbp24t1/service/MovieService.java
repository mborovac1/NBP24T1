package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface MovieService {

    List<MovieEntity> getAll();

    ResponseEntity deleteMovie(int id);

    ResponseEntity addMovie(MovieEntity movie);

    ByteArrayInputStream generateMoviesReportPdf();

    ByteArrayInputStream generatePriceReportPdf();
}
