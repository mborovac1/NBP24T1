package ba.team1.ads_project.repository;

import ba.team1.ads_project.entity.CinemaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaUserRepository extends JpaRepository<CinemaUser, Long> {
}
