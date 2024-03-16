package ba.team1.ads_project.repository;

import ba.team1.ads_project.entity.CinemaReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaReviewRepository extends JpaRepository<CinemaReview, Long> {
}
