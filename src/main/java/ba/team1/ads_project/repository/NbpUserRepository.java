package ba.team1.ads_project.repository;

import ba.team1.ads_project.entity.NbpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NbpUserRepository extends JpaRepository<NbpUser, Long> {
}
