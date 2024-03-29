package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.NbpLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NbpLogRepository extends JpaRepository<NbpLogEntity, Long> {
}
