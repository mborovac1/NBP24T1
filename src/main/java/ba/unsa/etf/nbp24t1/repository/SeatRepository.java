package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
}
