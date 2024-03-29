package ba.team1.ads_project.repository;

import ba.team1.ads_project.entity.CinemaReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaReviewRepository extends JpaRepository<CinemaReviewEntity, Long> {
}
