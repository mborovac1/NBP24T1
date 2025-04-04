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
@Table(name = "HALL", schema = "NBP24T1")
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
    @Column(name = "CAPACITY")
    private Integer capacity;

    @NotEmpty(message = "Hall number must exist.")
    @Positive(message = "Hall number must be a positive number.")
    @Column(name = "HALL_NUMBER", nullable = false, unique = true)
    private Integer hallNumber;

    @Column(name = "CINEMA_ID")
    private Long cinemaId;
}
