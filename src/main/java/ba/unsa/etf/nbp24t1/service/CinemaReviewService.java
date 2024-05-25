package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.CinemaReviewEntity;
import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CinemaReviewService {

    List<CinemaReviewEntity> getAll();

    ResponseEntity deleteCinemaReview(int id);

    ResponseEntity addCinemaReview(CinemaReviewEntity cinemaReview);
    //List<CinemaReviewEntity> findByCinemaUserId(int cinemaUserId);

}
