package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NbpUserRepository extends JpaRepository<NbpUserEntity, Long> {

    Boolean existsByEmail(String name);
    NbpUserEntity findByEmail(String email);
    NbpUserEntity findById(int id);
}
