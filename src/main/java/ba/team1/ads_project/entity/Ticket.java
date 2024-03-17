package ba.team1.ads_project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Ticket number must exist.")
    @Positive(message = "Ticket number must be a positive number.")
    @Column(name = "number", nullable = false)
    private Integer number;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @NotEmpty(message = "Ticket price must exist.")
    @Positive(message = "Ticket price must be a positive number.")
    @Column(name = "price", nullable = false)
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cinema_user_id")
    private CinemaUser cinemaUser;
}
