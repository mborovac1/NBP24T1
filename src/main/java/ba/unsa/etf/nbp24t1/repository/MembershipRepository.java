package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {

    Optional<MembershipEntity> findByType(String type);
    MembershipEntity findById(int id);
    Boolean existsById(int id);
}
