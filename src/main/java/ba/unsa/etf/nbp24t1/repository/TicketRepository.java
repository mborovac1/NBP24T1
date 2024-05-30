package ba.unsa.etf.nbp24t1.repository;

import ba.unsa.etf.nbp24t1.projection.PriceReportProjection;
import ba.unsa.etf.nbp24t1.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("SELECT t.seatNumber FROM TicketEntity t WHERE t.hallId = :hallId AND t.appointmentId = :appointmentId")
    List<Integer> findBookedSeatsByHallIdAndAppointmentId(Long hallId, Long appointmentId);

    @Query(value = """
            select m.NAME as movieName,
            m.PRICE as moviePrice,
            t.CINEMA_USER_ID as userId,
            a."start_time" as appointmentTime
            FROM TICKET t
            INNER JOIN APPOINTMENT a ON a.id = t.appointment_id
            INNER JOIN MOVIE m ON m.id = a."movie_id"
            WHERE a."start_time" >= :startDate
            GROUP BY a."start_time", m.PRICE, t.CINEMA_USER_ID, m.NAME
            """, nativeQuery = true)
    List<PriceReportProjection> getPriceReportData(@Param("startDate") LocalDateTime startDate);
}
