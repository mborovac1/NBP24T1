package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieReviewEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieReviewService {

    List<MovieReviewEntity> getAll();

    ResponseEntity<?> addMovieReview(MovieReviewEntity movieReview);
}
