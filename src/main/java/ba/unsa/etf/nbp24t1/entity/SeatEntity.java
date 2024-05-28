package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEAT", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Seat row must exist.")
    @Column(name = "SEAT_ROW", nullable = false, length = 2)
    private String row;

    @NotEmpty(message = "Seat number must exist.")
    @Positive(message = "Seat number must be a positive number.")
    @Column(name = "SEAT_NUMBER", nullable = false)
    private Integer number;

    @NotBlank(message = "Seat type must exist.")
    @Column(name = "SEAT_TYPE", nullable = false, length = 50)
    private String type;

    @Column(name = "HALL_ID")
    private Long hallId;
}
