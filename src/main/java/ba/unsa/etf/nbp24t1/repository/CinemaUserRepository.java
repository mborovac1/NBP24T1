package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.service.CinemaUserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaUserRepository extends JpaRepository<CinemaUserEntity, Long> {
    CinemaUserEntity findById(int id);

    Optional<CinemaUserEntity> findByUserId(Long userId);
    //Boolean existsByEmail(String email);
}
