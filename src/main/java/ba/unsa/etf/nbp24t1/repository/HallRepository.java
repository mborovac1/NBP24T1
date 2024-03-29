package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<HallEntity, Long> {
}
