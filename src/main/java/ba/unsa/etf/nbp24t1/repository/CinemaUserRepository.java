package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaUserRepository extends JpaRepository<CinemaUserEntity, Long> {
    //Boolean existsByEmail(String email);
}
