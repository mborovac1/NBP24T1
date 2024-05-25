package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.MovieReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, Long> {
    List<MovieReviewEntity> findByCinemaUserIdAndMovieId(Long cinemaUserId, Long movieId);
}
