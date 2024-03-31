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
@Table(name = "Ticket", schema = "NBP24T1")
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
    @Column(name = "ticket_number", nullable = false)
    private Integer number;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotEmpty(message = "Ticket price must exist.")
    @Positive(message = "Ticket price must be a positive number.")
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "movie_id")
    private Long movie_id;

    @Column(name = "seat_id")
    private Long cityId;

    @Column(name = "cinema_user_id")
    private Long cinemaUserId;
}
