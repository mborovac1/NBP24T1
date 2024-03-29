package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.MovieCinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCinemaRepository extends JpaRepository<MovieCinemaEntity, Long> {
}
