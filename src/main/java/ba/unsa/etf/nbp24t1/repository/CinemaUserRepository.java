package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.service.CinemaUserService;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaUserRepository extends JpaRepository<CinemaUserEntity, Long> {
    CinemaUserEntity findById(int id);

    Optional<CinemaUserEntity> findByUserId(Long userId);


    @Query(value = "DELETE FROM NBP24T1.CINEMAUSER WHERE 'user_id'=:userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") Long userId);
    //Boolean existsByEmail(String email);
}
