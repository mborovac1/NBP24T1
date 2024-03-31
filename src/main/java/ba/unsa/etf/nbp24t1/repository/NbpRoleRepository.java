package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.NbpRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NbpRoleRepository extends JpaRepository<NbpRoleEntity, Long> {

    Optional<NbpRoleEntity> findByName(String name);

    Boolean existsByName(String name);
}
