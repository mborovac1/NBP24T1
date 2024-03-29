package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
