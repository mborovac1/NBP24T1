package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seat", schema = "NBP24T1")
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
    @Column(name = "seat_row", nullable = false, length = 2)
    private String row;

    @NotEmpty(message = "Seat number must exist.")
    @Positive(message = "Seat number must be a positive number.")
    @Column(name = "seat_number", nullable = false)
    private Integer number;

    @NotBlank(message = "Seat type must exist.")
    @Column(name = "seat_type", nullable = false, length = 50)
    private String type;

    @Column(name = "hall_id")
    private Integer hallId;
}
