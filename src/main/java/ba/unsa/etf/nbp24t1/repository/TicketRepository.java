package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("SELECT t.seatNumber FROM TicketEntity t WHERE t.hallId = :hallId AND t.appointmentId = :appointmentId")
    List<Integer> findBookedSeatsByHallIdAndAppointmentId(Long hallId, Long appointmentId);
}
