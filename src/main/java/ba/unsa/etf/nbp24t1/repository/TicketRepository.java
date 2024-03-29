package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
