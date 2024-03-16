package ba.team1.ads_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "start_time")
    private LocalDate startTime;

    @NotEmpty(message = "Ticket price must exist.")
    @Positive(message = "Ticket price must be a positive number.")
    @Column(name = "price", nullable = false)
    private Double price;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
}
