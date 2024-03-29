package ba.team1.ads_project.repository;

import ba.team1.ads_project.entity.MovieReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, Long> {
}
