package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> findByName(String name);
}
