package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Hall", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "Hall capacity must exist.")
    @PositiveOrZero(message = "Hall capacity must be a non-negative number.")
    private Integer capacity;

    @NotEmpty(message = "Hall number must exist.")
    @Positive(message = "Hall number must be a positive number.")
    @Column(name = "hall_number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "cinema_id")
    private Integer cinemaId;
}
