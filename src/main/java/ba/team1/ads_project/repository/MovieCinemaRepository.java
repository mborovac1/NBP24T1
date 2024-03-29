package ba.team1.ads_project.repository;

import ba.team1.ads_project.entity.MovieCinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCinemaRepository extends JpaRepository<MovieCinemaEntity, Long> {
}
