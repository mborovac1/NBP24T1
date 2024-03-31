package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.NbpRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NbpRoleRepository extends JpaRepository<NbpRoleEntity, Long> {

    Boolean existsByName(String name);
}
