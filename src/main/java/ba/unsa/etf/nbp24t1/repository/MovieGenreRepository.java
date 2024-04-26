package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.MovieGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenreEntity, Long> {
    List<MovieGenreEntity> findByMovieId(Long movieId);
}
