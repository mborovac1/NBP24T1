package ba.unsa.etf.nbp24t1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TICKET", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "Ticket number must exist.")
    @Positive(message = "Ticket number must be a positive number.")
    @Column(name = "TICKET_NUMBER", nullable = false)
    private Integer ticketNumber;


    @Column(name = "APPOINTMENT_ID")
    private Long appointmentId;

    @Column(name = "HALL_ID")
    private Long hallId;

    @Column(name = "SEAT_NUMBER")
    private Integer seatNumber;

    @Column(name = "CINEMA_USER_ID")
    private Long cinemaUserId;
}
